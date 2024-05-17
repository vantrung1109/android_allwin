package com.base.mvvm.ui.fragment.home.discount;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.data.Repository;
import com.base.mvvm.data.model.api.response.discount.DiscountResponse;
import com.base.mvvm.ui.base.BaseViewModel;
import com.base.mvvm.ui.fragment.HomeCallBack;
import com.base.mvvm.utils.NetworkUtils;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.Setter;

public class DiscountViewModel extends BaseViewModel {
    public DiscountViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }

    public void onBack(){
        application.getCurrentActivity().finish();
    }

    @Setter
    public HomeCallBack callBack;


    public void callDiscount(int page, int size){
        compositeDisposable.add(repository.getApiService()
                .getDiscount(null,
                        null,
                        page,
                        size,
                        null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(throwable ->
                        throwable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(Throwable throwable) throws Throwable {
                                if (NetworkUtils.checkNetworkError(throwable)) {
                                    return application.showDialogNoInternetAccess();
                                }
                                else {
                                    return Observable.error(throwable);
                                }
                            }
                        })
                )
                .subscribe(response -> {
                    if (response.isResult()) {
                        showSuccessMessage("Call Api Get Discount Successfully");
                        if (callBack != null) {
                            callBack.doSuccessGetData(response.getData().getContent());
                        }
                    }
                }, throwable -> {
                    showErrorMessage(throwable.getMessage());
                    hideLoading();
                        }

                )


        );
    }

}
