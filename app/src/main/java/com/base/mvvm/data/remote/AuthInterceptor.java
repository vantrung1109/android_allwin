package com.base.mvvm.data.remote;

import android.app.Application;
import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.base.mvvm.BuildConfig;
import com.base.mvvm.constant.Constants;
import com.base.mvvm.data.local.prefs.PreferencesService;
import com.base.mvvm.utils.LogService;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private final PreferencesService appPreferences;
    private final Application application;

    public AuthInterceptor(PreferencesService appPreferences, Application application) {
        this.appPreferences = appPreferences;
        this.application = application;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
        String isIgnore = chain.request().header("IgnoreAuth");
        if (isIgnore != null && isIgnore.equals("1")) {
            Request.Builder newRequest = chain.request().newBuilder();
            newRequest.removeHeader("IgnoreAuth");
            return chain.proceed(newRequest.build());
        }

        //Add Authentication
        Request.Builder newRequest = chain.request().newBuilder();
        String token = appPreferences.getToken();
        if (token != null && !token.equals("")) {
            newRequest.addHeader("Authorization", "Bearer " + token);
        }

        Response origResponse = chain.proceed(newRequest.build());
        if (origResponse.code() == 403 || origResponse.code() == 401) {
            LogService.i("Error http =====================> code: "+origResponse.code());
            appPreferences.removeKey(PreferencesService.KEY_BEARER_TOKEN);
            Intent intent = new Intent();
            intent.setAction(Constants.ACTION_EXPIRED_TOKEN);
            LocalBroadcastManager.getInstance(application.getApplicationContext()).sendBroadcast(intent);
        }


        String isMediaKind = chain.request().header("isMedia");
        if(isMediaKind != null && isMediaKind.equals("1")){
            StringBuilder builder = new StringBuilder(BuildConfig.MEDIA_URL);
            for (String seg: chain.request().url().pathSegments()) {
                builder.append(seg).append('/');
            }
            newRequest.removeHeader("isMedia");
            return chain.proceed(newRequest.url(builder.toString()).build());
        }
        return origResponse;
    }
}
