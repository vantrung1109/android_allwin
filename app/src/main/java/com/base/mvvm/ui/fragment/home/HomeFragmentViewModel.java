package com.base.mvvm.ui.fragment.home;

import android.util.Log;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.R;
import com.base.mvvm.data.Repository;
import com.base.mvvm.ui.base.BaseFragmentViewModel;
import com.base.mvvm.ui.fragment.HomeCallBack;
import com.base.mvvm.utils.NetworkUtils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.Setter;

public class HomeFragmentViewModel extends BaseFragmentViewModel {


    public HomeFragmentViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);

    }
    @Setter
    HomeCallBack callBack;

    public void onBackClick() {
        application.getCurrentActivity().finish();
    }

    public void getSearchPlaces(String text){
        compositeDisposable.add(repository.getApiService().getSearchPlacesGG(
                        text + " Việt Nam",
                        "AIzaSyDQFJ-AGut2GL97rVRvf2q1SJLwABJjWOU")
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
                        Log.e("getSearchPlaces", response.getPredictions().toString());
                    }
                    hideLoading();
                }, throwable -> {
                    Log.d("TAG", "callApiGetMyBooking: " + throwable.getMessage());
                    showErrorMessage(application.getResources().getString(R.string.no_internet));
                    hideLoading();
                }));
    }



}
