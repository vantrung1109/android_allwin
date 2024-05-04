package com.base.mvvm;

import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;

import com.base.mvvm.data.websocket.Message;
import com.base.mvvm.data.websocket.SocketEventModel;
import com.base.mvvm.data.websocket.SocketListener;
import com.base.mvvm.data.websocket.WebSocketLiveData;
import com.base.mvvm.di.component.AppComponent;
import com.base.mvvm.di.component.DaggerAppComponent;
import com.base.mvvm.others.MyTimberDebugTree;
import com.base.mvvm.others.MyTimberReleaseTree;
import com.base.mvvm.utils.DialogUtils;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import es.dmoral.toasty.Toasty;
import io.reactivex.rxjava3.subjects.PublishSubject;
import lombok.Getter;
import lombok.Setter;
import timber.log.Timber;

public class MVVMApplication extends Application implements SocketListener {
    @Getter
    @Setter
    private AppCompatActivity currentActivity;

    @Setter
    @Getter
    private AppComponent appComponent;

    @Setter
    @Getter
    private WebSocketLiveData mWebSocketLiveData;


    @Override
    public void onCreate() {
        super.onCreate();

        // Enable firebase log
        FirebaseCrashlytics firebaseCrashlytics = FirebaseCrashlytics.getInstance();
        firebaseCrashlytics.setCrashlyticsCollectionEnabled(true);


        if (BuildConfig.DEBUG) {
            Timber.plant(new MyTimberDebugTree());
        }else{
            Timber.plant(new MyTimberReleaseTree(firebaseCrashlytics));
        }

        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build();
        appComponent.inject(this);

        // Init Toasty
        Toasty.Config.getInstance()
                .allowQueue(false)
                .apply();

        mWebSocketLiveData = WebSocketLiveData.getInstance();
        mWebSocketLiveData.setSocketListener(this);
        mWebSocketLiveData.setAppOnline(true);


    }




    public PublishSubject<Integer> showDialogNoInternetAccess(){
        final PublishSubject<Integer> subject = PublishSubject.create();
        currentActivity.runOnUiThread(() ->
                DialogUtils.dialogConfirm(currentActivity, currentActivity.getResources().getString(R.string.newtwork_error),
                        currentActivity.getResources().getString(R.string.newtwork_error_button_retry),
                        (dialogInterface, i) -> subject.onNext(1), currentActivity.getResources().getString(R.string.newtwork_error_button_exit),
                        (dialogInterface, i) -> System.exit(0))
        );
        return subject;
    }

    public void startSocket(String token){
        mWebSocketLiveData.setSession(token);
        mWebSocketLiveData.startSocket();

    }

    public void stopSocket(){
        mWebSocketLiveData.setSession(null);
        mWebSocketLiveData.stopSocket();
    }

    @Override
    public void onMessage(SocketEventModel socketEventModel) {

    }

    @Override
    public void onTimeout(Message message) {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onClosed() {

    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onSessionExpire() {

    }

    @Override
    public void onPingFailure() {

    }
}
