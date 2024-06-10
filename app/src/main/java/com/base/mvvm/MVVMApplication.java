package com.base.mvvm;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.base.mvvm.data.model.websocket.BookingAccepted;
import com.base.mvvm.data.websocket.Command;
import com.base.mvvm.data.websocket.Message;
import com.base.mvvm.data.websocket.SocketEventModel;
import com.base.mvvm.data.websocket.SocketListener;
import com.base.mvvm.data.websocket.WebSocketLiveData;
import com.base.mvvm.di.component.AppComponent;
import com.base.mvvm.di.component.DaggerAppComponent;
import com.base.mvvm.others.MyTimberDebugTree;
import com.base.mvvm.others.MyTimberReleaseTree;
import com.base.mvvm.ui.fragment.home.booking_done.BookingDoneActivity;
import com.base.mvvm.ui.fragment.home.maps.MapActivity;
import com.base.mvvm.utils.DialogUtils;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import java.io.Serializable;

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
       getCurrentActivity().runOnUiThread(() -> handleSocket(socketEventModel));
    }

    private void handleSocket(SocketEventModel socketEventModel) {
        if (socketEventModel.getEvent().equals(SocketEventModel.EVENT_MESSAGE)){
            Message message = socketEventModel.getMessage();
            if (message.getResponseCode() == 200){
                switch (message.getCmd()){
                    case Command.COMMAND_DRIVER_ACCEPT:
                        Log.e("Socket", "Driver accepted");
                        Intent intent = new Intent(currentActivity, MapActivity.class);
                        Bundle bundle = new Bundle();
                        BookingAccepted bookingAccepted = message.getDataObject(BookingAccepted.class);
                        bundle.putSerializable("data", bookingAccepted);
                        intent.putExtras(bundle);
                        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        currentActivity.startActivity(intent);
                        break;
                    case Command.COMMAND_DRIVER_DONE:
                        Intent intent_driver_done = new Intent(currentActivity, BookingDoneActivity.class);
                        Bundle bundle1 = new Bundle();
                        currentActivity.startActivity(intent_driver_done);
                        Log.e("Socket", "Driver cancel");
                        break;
                    default:
                        break;
                }
            }
        }
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
