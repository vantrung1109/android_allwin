package com.base.mvvm.ui.fragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.R;
import com.base.mvvm.data.Repository;
import com.base.mvvm.data.model.api.response.booking.MyBookingResponse;
import com.base.mvvm.ui.base.BaseFragmentViewModel;
import com.base.mvvm.ui.fragment.activity.adapter.BookingDetail;
import com.base.mvvm.ui.main.MainActivity;
import com.base.mvvm.utils.NetworkUtils;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ActivityFragmentViewModel extends BaseFragmentViewModel {
    public ActivityFragmentViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }

    MutableLiveData<List<MyBookingResponse>> listBooking = new MutableLiveData<>();

    public void haha() {
        //do something
        System.out.println("ActivityFragmentViewModel");
    }

    public void callApiGetMyBooking(){

        compositeDisposable.add(repository.getApiService().getMyBooking(
                        null,
                        null,
                        null,
                        null,
                        null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(throwable ->
                        throwable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(Throwable throwable) throws Throwable {
                                if (NetworkUtils.checkNetworkError(throwable)) {
                                    return application.showDialogNoInternetAccess();
                                }else{
                                    return Observable.error(throwable);
                                }
                            }
                        })
                )
                .subscribe(response -> {
                    if(response.isResult()){
                        listBooking.setValue(response.getData().getContent());
                        showSuccessMessage(application.getResources().getString(R.string.login_success));
                    }else{
                        showErrorMessage(response.getMessage());
                    }
                    hideLoading();
                }, throwable -> {
                    showErrorMessage(application.getResources().getString(R.string.no_internet));
                    hideLoading();
                }));
    }
}
