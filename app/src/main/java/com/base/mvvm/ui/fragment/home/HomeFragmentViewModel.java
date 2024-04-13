package com.base.mvvm.ui.fragment.home;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.R;
import com.base.mvvm.data.Repository;
import com.base.mvvm.data.model.api.api_search.SearchPlaceApi;
import com.base.mvvm.ui.base.BaseFragmentViewModel;
import com.base.mvvm.utils.NetworkUtils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeFragmentViewModel extends BaseFragmentViewModel {


    public MutableLiveData<SearchPlaceApi> objectSearchPlaces = new MutableLiveData<>();
    public HomeFragmentViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
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
                        showSuccessMessage("Call Api Get My Booking Successfully");
                        objectSearchPlaces.setValue(response);
                    }else{
                        showErrorMessage(response.getStatus());
                    }
                    hideLoading();
                }, throwable -> {
                    Log.d("TAG", "callApiGetMyBooking: " + throwable.getMessage());
                    showErrorMessage(application.getResources().getString(R.string.no_internet));
                    hideLoading();
                }));
    }
}
