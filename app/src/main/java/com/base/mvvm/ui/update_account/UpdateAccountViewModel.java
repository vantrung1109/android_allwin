package com.base.mvvm.ui.update_account;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.databinding.ObservableField;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.R;
import com.base.mvvm.data.Repository;
import com.base.mvvm.data.model.api.request.SigninRequest;
import com.base.mvvm.data.model.api.response.AccountResponse;
import com.base.mvvm.data.model.db.AccountEntity;
import com.base.mvvm.ui.base.BaseViewModel;
import com.base.mvvm.ui.main.MainActivity;
import com.base.mvvm.ui.signup.SignUpActivity;
import com.base.mvvm.utils.NetworkUtils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UpdateAccountViewModel extends BaseViewModel {
    public ObservableField<Boolean> isShowPassWord = new ObservableField<>(false);
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<AccountResponse> profile = new ObservableField<>(new AccountResponse());

    public UpdateAccountViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
        doUpdateAccount();
    }

    public void doUpdateAccount(){
//        if (password.get() == null || password.get().isEmpty()){
//            showErrorMessage("Mật khẩu không được để trống");
//            return;
//        }

        showLoading();
        compositeDisposable.add(repository.getApiService().profile2()
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
                        profile.set(response.getData());
                        //repository.getSqliteService().insertAccount(profile.get());
                        showSuccessMessage("Get Profile Information Successfully");

                    }else{
                        showErrorMessage(response.getMessage());
                    }
                    hideLoading();
                }, throwable -> {
                    showErrorMessage(application.getResources().getString(R.string.no_internet));
                    hideLoading();
                }));
    }

    public void showPassword(){
        isShowPassWord.set(!isShowPassWord.get());
    }

    public void onBack(){
        application.startActivity(new Intent(application, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

}
