package com.base.mvvm.ui.update_account;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.databinding.ObservableField;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.R;
import com.base.mvvm.data.Repository;
import com.base.mvvm.data.model.api.ResponseWrapper;
import com.base.mvvm.data.model.api.request.UpdateProfileRequest;
import com.base.mvvm.data.model.api.response.AccountResponse;
import com.base.mvvm.ui.base.BaseViewModel;
import com.base.mvvm.ui.main.MainActivity;
import com.base.mvvm.utils.NetworkUtils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.RequestBody;

public class UpdateAccountViewModel extends BaseViewModel {
    public ObservableField<Boolean> isShowPassWord = new ObservableField<>(false);
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<AccountResponse> profile = new ObservableField<>(new AccountResponse());

    public UpdateAccountViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
        getProfileFromApi();
    }

//    public Observable<ResponseWrapper<AccountResponse>> getProfile(){
//        return repository.getApiService().getProfile()
//                .doOnNext(response -> {
//                    profile.set(response.getData());
//                });
//    }
    public void updateProfile(){
        showLoading();
        UpdateProfileRequest request = new UpdateProfileRequest();
        request.setName(profile.get().getName());
        request.setEmail(profile.get().getEmail());
        request.setNewPassword(password.get());
        request.setOldPassword(password.get());
        Log.e("updateProfile: ", request.toString());
        compositeDisposable.add(repository.getApiService().updateProfile(request)
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

                        showSuccessMessage(application.getString(R.string.update_profile_successfully));

                    }else{
                        showErrorMessage(response.getMessage());
                    }
                    hideLoading();
                }, throwable -> {
                    showErrorMessage(application.getResources().getString(R.string.no_internet));
                    hideLoading();
                }));
    }

    public Observable<ResponseWrapper<AccountResponse>> uploadAvatar(RequestBody requestBody){
        return repository.getApiService().uploadFile(requestBody);
    }


    public void showPassword(){
        isShowPassWord.set(!isShowPassWord.get());
    }

    public void onBack(){
        application.startActivity(new Intent(application, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
    public void getProfileFromApi(){
        showLoading();
        compositeDisposable.add(repository.getApiService().getProfile()
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

}
