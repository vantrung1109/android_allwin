package com.base.mvvm.ui.fragment.home.maps;

import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.R;
import com.base.mvvm.data.Repository;
import com.base.mvvm.data.model.api.response.map.address_by_placeid.Location;
import com.base.mvvm.data.model.api.request.BookingCreateRequest;
import com.base.mvvm.data.model.api.response.service.ServiceResponse;
import com.base.mvvm.ui.base.BaseViewModel;
import com.base.mvvm.ui.fragment.HomeCallBack;
import com.base.mvvm.data.model.api.response.service.ServicePrice;
import com.base.mvvm.ui.fragment.home.payment_method.PaymentMethodActivity;
import com.base.mvvm.utils.NetworkUtils;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MapViewModel extends BaseViewModel {
    HomeCallBack callBack;

    public MutableLiveData<Location> locationOrigin = new MutableLiveData<>();
    public MutableLiveData<Location> locationDestination = new MutableLiveData<>();
    public MutableLiveData<String> distance = new MutableLiveData<>();

    public MutableLiveData<BookingCreateRequest> bookingCreateRequest = new MutableLiveData<>();

    public MapViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);

    }

    public void setListenerCallBack(HomeCallBack callBack) {
        this.callBack = callBack;
    }

    public void getServices(double distance){
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
                        List<ServiceResponse> serviceResponses = response.getData().getContent();

                        Gson gson = new Gson();
                        for (ServiceResponse serviceResponse : serviceResponses) {
                            ServicePrice servicePrice = gson.fromJson(serviceResponse.getPrice(), ServicePrice.class);
                            serviceResponse.setPrice(ServicePrice.calculatePrice(distance, servicePrice) + "");
                        }

                        callBack.doSuccessGetData(serviceResponses);

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

    public void getAddressByPlaceId (String originId, String destinationId){
        showLoading();
        compositeDisposable.add(repository.getApiService()
                .getDetailAddress(originId,
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
                        locationOrigin.setValue(response.getResults().get(0).getGeometry().getLocation());
                        bookingCreateRequest.setValue(new BookingCreateRequest());
                        bookingCreateRequest.getValue().setPickupAddress(response.getResults().get(0).getFormatted_address());
                        bookingCreateRequest.getValue().setPickupLat(response.getResults().get(0).getGeometry().getLocation().getLat());
                        bookingCreateRequest.getValue().setPickupLong(response.getResults().get(0).getGeometry().getLocation().getLng());
                        bookingCreateRequest.getValue().setPaymentKind(1);
                        getDestinationAddressByPlaceId(destinationId);
                    }
                    hideLoading();
                }, throwable -> {
                    showErrorMessage(application.getResources().getString(R.string.no_internet));
                    hideLoading();
                })
        );
    }
    public void getDestinationAddressByPlaceId (String destinationId){
        showLoading();
        compositeDisposable.add(repository.getApiService()
                .getDetailAddress(destinationId,
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
                        locationDestination.setValue(response.getResults().get(0).getGeometry().getLocation());
                        bookingCreateRequest.getValue().setDestinationAddress(response.getResults().get(0).getFormatted_address());
                        bookingCreateRequest.getValue().setDestinationLat(response.getResults().get(0).getGeometry().getLocation().getLat());
                        bookingCreateRequest.getValue().setDestinationLong(response.getResults().get(0).getGeometry().getLocation().getLng());
                        getDistance(
                                locationOrigin.getValue().getLat() + "," + locationOrigin.getValue().getLng(),
                                locationDestination.getValue().getLat() + "," + locationDestination.getValue().getLng());
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
                .observeOn(AndroidSchedulers.mainThread())
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
                        String distanceString = distanceResponse.getRows().get(0).getElements().get(0).getDistance().getText();
                        String numericValue = distanceString.replaceAll("[^0-9.]", "");
                        double distance = Double.parseDouble(numericValue);
                        distance = distance * 1000;
                        bookingCreateRequest.getValue().setDistance(distance);
                        getServices(distance);

                    }
                    hideLoading();
                }, throwable -> {
                    showErrorMessage(application.getResources().getString(R.string.no_internet));
                    hideLoading();
                })
        );
    }

    public void createBookingRequest(){
        showLoading();

        compositeDisposable.add(repository.getApiService()
                .createBooking(bookingCreateRequest.getValue())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
                .subscribe(response -> {
                    if (response.isResult()){
                        application.getMWebSocketLiveData().getCodeBooking().add(response.getData().getCode());

                        application.getMWebSocketLiveData().sendPing();
                        Log.e("createBooking", response.toString());

                        callBack.doSuccessGetData(response.getData());
//                        navigateToPaymentMethod();
                    } else {
                        showErrorMessage(response.getMessage());
                    }
                    hideLoading();
                }, throwable -> {
                    showErrorMessage(application.getResources().getString(R.string.no_internet));
                    hideLoading();
                })
        );

    }

    public void getDirection (String origin, String destination){
        showLoading();
        compositeDisposable.add(repository.getApiService()
                .getMapDirection(origin,
                        "driving",
                        destination,
                        application.getString(R.string.str_gg_api))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
                .subscribe(directionResponse -> {
                    callBack.doSuccessGetData(directionResponse);
                    hideLoading();
                }, throwable -> {
                    Log.e("getDirection", throwable.getMessage());
                    showErrorMessage(application.getResources().getString(R.string.no_internet));
                    hideLoading();
                })
        );
    }

    public void loadingBooking(Long bookingId){
        showLoading();
        compositeDisposable.add(repository.getApiService()
                .loadBooking(bookingId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
                .subscribe(response -> {
                    if (response.isResult()){
                        callBack.doSuccessGetData(response.getData());
                    } else {
                        Log.e("loadBooking", response.getMessage());
                        showErrorMessage(response.getMessage());
                    }
                    hideLoading();
                }, throwable -> {
                    Log.e("loadBooking", throwable.getMessage());
                    showErrorMessage(application.getResources().getString(R.string.no_internet));
                    hideLoading();
                })
        );
    }

    public void navigateToPaymentMethod(){
        Intent intent = new Intent(application, PaymentMethodActivity.class);
        application.startActivity(intent);
    }



}
