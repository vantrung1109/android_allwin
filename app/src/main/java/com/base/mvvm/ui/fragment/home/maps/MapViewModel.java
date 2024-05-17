package com.base.mvvm.ui.fragment.home.maps;

import android.content.Intent;
import android.util.Log;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.R;
import com.base.mvvm.data.Repository;
import com.base.mvvm.data.model.api.address_by_placeid.Location;
import com.base.mvvm.data.model.api.response.service.ServiceResponse;
import com.base.mvvm.ui.base.BaseViewModel;
import com.base.mvvm.ui.fragment.HomeCallBack;
import com.base.mvvm.ui.fragment.home.discount.DiscountActivity;
import com.base.mvvm.ui.fragment.home.note.NoteActivity;
import com.base.mvvm.ui.fragment.home.payment_method.PaymentMethodActivity;
import com.base.mvvm.utils.NetworkUtils;
import com.google.android.datatransport.runtime.Destination;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MapViewModel extends BaseViewModel {
    HomeCallBack callBack;


    public MapViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);

    }

    public void setListenerCallBack(HomeCallBack callBack) {
        this.callBack = callBack;
    }

    public void getServices(){
        showLoading();
        compositeDisposable.add(repository.getApiService().getServices()
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
                        showSuccessMessage("Call Api Get services successfully!");
                        callBack.doSuccessGetData(response.getData().getContent());
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

    public void getAddressByPlaceId (String placeId){
        showLoading();
        compositeDisposable.add(repository.getApiService()
                .getDetailAddress(placeId,
                        application.getString(R.string.str_gg_api))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(throwable -> {
                    return throwable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                        @Override
                        public ObservableSource<?> apply(Throwable throwable) throws Throwable {
                            if (NetworkUtils.checkNetworkError(throwable)){
                                return application.showDialogNoInternetAccess();
                            } else
                                return Observable.error(throwable);
                        }
                    });
                })
                .subscribe(response -> {
                    if (response.getStatus().equals("OK")){
                        Log.e("getDetailAddress", response.getResults().toString());
                        callBack.doSuccessGetData(response);
                    }
                    hideLoading();
                }, throwable -> {
                    showErrorMessage(application.getResources().getString(R.string.no_internet));
                    hideLoading();
                })
        );
    }

    public void getDistance(String destinations, String origins){
        showLoading();
        compositeDisposable.add(repository.getApiService()
                .getDistance(destinations,
                        origins,
                        application.getString(R.string.str_gg_api))
                .subscribeOn(Schedulers.io())
                .retryWhen(throwable ->{
                    return throwable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                        @Override
                        public ObservableSource<?> apply(Throwable throwable) throws Throwable {
                            if (NetworkUtils.checkNetworkError(throwable)){
                                return application.showDialogNoInternetAccess();
                            } else
                                return Observable.error(throwable);
                        }
                    });
                })
                .subscribe(distanceResponse -> {
                    if (distanceResponse.getStatus().equals("OK")){
                        Log.e("getDistance", distanceResponse.toString());
                        callBack.doSuccessGetData(distanceResponse);

                    }
                    hideLoading();
                }, throwable -> {
                    showErrorMessage(application.getResources().getString(R.string.no_internet));
                    hideLoading();
                })
        );
    }



    public void navigateToPaymentMethod(){
        Intent intent = new Intent(application, PaymentMethodActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        application.startActivity(intent);
    }
    public void navigateToDiscount(){
        Intent intent = new Intent(application, DiscountActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        application.startActivity(intent);
    }
    public void navigateToNote(){
        Intent intent = new Intent(application, NoteActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        application.startActivity(intent);
    }
    public void onBack(){
        application.getCurrentActivity().finish();
    }
}
