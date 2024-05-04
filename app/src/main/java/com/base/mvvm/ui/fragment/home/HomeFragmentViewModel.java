package com.base.mvvm.ui.fragment.home;

import android.content.Intent;
import android.util.Log;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.R;
import com.base.mvvm.data.Repository;
import com.base.mvvm.data.model.api.api_search.SearchPlaceApi;
import com.base.mvvm.ui.base.BaseFragmentViewModel;
import com.base.mvvm.ui.fragment.InterfaceCallBackApi;
import com.base.mvvm.ui.fragment.home.maps.MapActivity;
import com.base.mvvm.utils.NetworkUtils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeFragmentViewModel extends BaseFragmentViewModel {


    public HomeFragmentViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);

    }
    InterfaceCallBackApi<SearchPlaceApi> callBack;
    public void setListenerCallBack(InterfaceCallBackApi<SearchPlaceApi> callBack) {
        this.callBack = callBack;
    }

    public void onBackClick() {
        application.getCurrentActivity().finish();
    }

    public void getSearchPlaces(String text){
        compositeDisposable.add(repository.getApiService().getSearchPlacesGG(
                        text + " Việt Nam",
                        "AIzaSyAQWUevZCTLaVd9a1Z2WEA2_e2gO9iW8rU")
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
                    if(response.getStatus().equals("OK")){
                        callBack.doSuccessGetData(response);
                    }
                    hideLoading();
                }, throwable -> {
                    Log.d("TAG", "callApiGetMyBooking: " + throwable.getMessage());
                    showErrorMessage(application.getResources().getString(R.string.no_internet));
                    hideLoading();
                }));
    }
    public void  doContinue(){
        Intent intent = new Intent(application, MapActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        application.startActivity(intent);
    }
}
