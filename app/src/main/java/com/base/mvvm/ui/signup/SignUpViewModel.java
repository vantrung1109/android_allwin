package com.base.mvvm.ui.signup;

import android.content.Intent;

import androidx.databinding.ObservableField;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.R;
import com.base.mvvm.data.Repository;
import com.base.mvvm.data.model.api.request.SignUpRequest;
import com.base.mvvm.data.model.api.request.SigninRequest;
import com.base.mvvm.ui.base.BaseViewModel;
import com.base.mvvm.ui.main.MainActivity;
import com.base.mvvm.ui.signin.SignInActivity;
import com.base.mvvm.utils.NetworkUtils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SignUpViewModel extends BaseViewModel {

    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> phone = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<Boolean> isShowPassWord = new ObservableField<>(false);

    public void doSignup(){
        if (name.get() == null ||  name.get().isEmpty()) {
            showErrorMessage("Tên không được để trốngg");
            return;
        }
        if (email.get() == null ||  email.get().isEmpty()) {
            showErrorMessage("Email không được để trống");
            return;
        }
        if (email.get() == null || !android.util.Patterns.EMAIL_ADDRESS.matcher(email.get()).matches()) {
            showErrorMessage("Email không đúng định dạng");
            return;
        }

        if (phone.get() == null || phone.get().isEmpty()) {
            showErrorMessage("Số điện thoại không được để trống");
            return;
        }
        if (password.get() == null || password.get().isEmpty()) {
            showErrorMessage("Mật khẩu không được để trống");
            return;
        }

        showLoading();
        SignUpRequest request = new SignUpRequest(
                String.valueOf(name.get()),
                String.valueOf(email.get()),
                String.valueOf(phone.get()),
                String.valueOf(password.get()));

        compositeDisposable.add(repository.getApiService().signup(request)
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
                        showSuccessMessage("Đăng ký thành công");
                        Intent intent = new Intent(application, SignInActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        application.startActivity(intent);
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

    public SignUpViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }
}
