package com.base.mvvm.ui.fragment.activity;

import android.util.Log;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.R;
import com.base.mvvm.data.Repository;
import com.base.mvvm.data.model.api.response.booking.MyBookingResponse;

import com.base.mvvm.ui.base.BaseFragmentViewModel;
import com.base.mvvm.ui.fragment.InterfaceCallBackApi;
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

    InterfaceCallBackApi<List<MyBookingResponse>> callBack;

    public void setListenerCallBack(InterfaceCallBackApi<List<MyBookingResponse>> callBack) {
        this.callBack = callBack;
    }

    public void callApiGetMyBooking(Integer pageSize, Integer  pageNumber){
        showLoading();
        compositeDisposable.add(repository.getApiService().getMyBooking(
                        null,
                        null,
                        pageNumber,
                        pageSize,
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
                        showSuccessMessage("Call Api Get My Booking Successfully");
                        if (callBack != null) {
                            callBack.doSuccessGetData(response.getData().getContent());
                        }
                    }else{
                        showErrorMessage(response.getMessage());
                    }
                    hideLoading();
                }, throwable -> {
                    Log.d("TAG", "callApiGetMyBooking: " + throwable.getMessage());
                    showErrorMessage(application.getResources().getString(R.string.no_internet));
                    hideLoading();
                }));
    }

    public void callApiGetMyBooking2(Integer pageSize, Integer  pageNumber){
        showLoading();
        compositeDisposable.add(repository.getApiService().getMyBooking(
                        null,
                        null,
                        pageNumber,
                        pageSize,
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
                        showSuccessMessage("Call Api Get My Booking Successfully");
                        //listBooking.setValue(response.getData().getContent());
                        //listMyBookings.setValue(response.getData().getContent());
                    }else{
                        showErrorMessage(response.getMessage());
                    }
                    hideLoading();
                }, throwable -> {
                    Log.d("TAG", "callApiGetMyBooking: " + throwable.getMessage());
                    showErrorMessage(application.getResources().getString(R.string.no_internet));
                    hideLoading();
                }));
    }

    @Override
    public void showLoading() {
        super.showLoading();
        Log.e("BaseFragment", "activity showLoading: ");
    }


}
