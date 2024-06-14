package com.base.mvvm.ui.fragment.home.booking_pickup_success;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.data.Repository;
import com.base.mvvm.ui.base.BaseViewModel;
import com.base.mvvm.ui.fragment.HomeCallBack;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.Getter;
import lombok.Setter;

public class BookingPickUpSuccessViewModel extends BaseViewModel {

    @Getter
    @Setter
    HomeCallBack callBack;

    public MutableLiveData<String> code = new MutableLiveData<>();

    public void setListenerCallBack(HomeCallBack callBack) {
        this.callBack = callBack;
    }

    public BookingPickUpSuccessViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }

    public void getMyCurrentBooking() {
        compositeDisposable.add(repository.getApiService().getMyCurrentBooking()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response.isResult()) {
                        showSuccessMessage("Call Api Get My Current Booking Successfully");
                        if (callBack != null) {
                            Log.e("BookingPickUpSuccessViewModel", "doSuccessGetData: " + response.getData().getContent());
                            callBack.doSuccessGetData(response.getData().getContent());
                        }

                    } else {
                        showErrorMessage("Call Api Get My Current Booking Failed");
                    }
                }, throwable -> {
                    Log.e("BookingPickUpSuccessViewModel", "getMyCurrentBooking: " + throwable.getMessage());
                    showErrorMessage(throwable.getMessage());
                })
        );
    }
}
