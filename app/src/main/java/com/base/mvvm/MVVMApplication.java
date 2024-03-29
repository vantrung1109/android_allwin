package com.base.mvvm;

import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;

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

public class MVVMApplication extends Application{
    @Setter
    private AppCompatActivity currentActivity;

    @Getter
    private AppComponent appComponent;

    public AppCompatActivity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(AppCompatActivity currentActivity) {
        this.currentActivity = currentActivity;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public void setAppComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }

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
}
