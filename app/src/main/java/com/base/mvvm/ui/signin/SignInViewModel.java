package com.base.mvvm.ui.signin;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import androidx.databinding.ObservableField;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.R;
import com.base.mvvm.data.Repository;
import com.base.mvvm.data.model.api.request.LoginRequest;
import com.base.mvvm.data.model.api.request.SigninRequest;
import com.base.mvvm.databinding.ActivitySigninBinding;
import com.base.mvvm.ui.base.BaseViewModel;
import com.base.mvvm.ui.fragment.account.AccountFragment;
import com.base.mvvm.ui.login.LoginCallback;
import com.base.mvvm.ui.main.MainActivity;
import com.base.mvvm.ui.signup.SignUpActivity;
import com.base.mvvm.utils.NetworkUtils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SignInViewModel extends BaseViewModel {

    public ObservableField<Boolean> isShowPassWord = new ObservableField<>(false);
    public ObservableField<String> phone = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();

    public void doLogin(){

        if (phone.get() == null || phone.get().isEmpty()){
            showErrorMessage("Số điện thoại không được để trống");
            return;
        }
        if (password.get()== null || password.get().isEmpty()){
            showErrorMessage("Mật khẩu không được để trống");
            return;
        }

        showLoading();
        SigninRequest request = new SigninRequest(String.valueOf(phone.get()), String.valueOf(password.get()));

        compositeDisposable.add(repository.getApiService().login2(request)
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
                        repository.setToken(response.getData().getAccess_token());
                        Log.d("token", response.getData().getAccess_token());
                        showSuccessMessage(application.getResources().getString(R.string.login_success));
                        Intent intent = new Intent(application, MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("access_token", response.getData().getAccess_token());
                        intent.putExtras(bundle);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        application.startActivity(intent, bundle);
                    }else{
                        showErrorMessage(response.getMessage());
                    }
                    hideLoading();
                }, throwable -> {
                    Log.e("error", throwable.getMessage());
                    showErrorMessage(application.getResources().getString(R.string.no_internet));
                    hideLoading();
                }));
    }
    public void doSignup(){
        Intent intent = new Intent(application, SignUpActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        application.startActivity(intent);
    }

    public void showPassword(){
        isShowPassWord.set(!isShowPassWord.get());
    }

    public SignInViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);



    }
}
