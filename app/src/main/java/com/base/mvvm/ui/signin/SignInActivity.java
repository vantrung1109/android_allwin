package com.base.mvvm.ui.signin;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.Observable;

import com.base.mvvm.BR;
import com.base.mvvm.R;
import com.base.mvvm.databinding.ActivitySigninBinding;
import com.base.mvvm.di.component.ActivityComponent;
import com.base.mvvm.ui.base.BaseActivity;

public class SignInActivity extends BaseActivity<ActivitySigninBinding, SignInViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_signin;
    }
    @Override
    public int getBindingVariable() {
        return BR.vm;
    }
    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("View model", "onCreateSigninActivity: " + viewModel);
        viewModel.isShowPassWord.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if(!viewModel.isShowPassWord.get()){
                    viewBinding.editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else {
                    viewBinding.editPassword.setTransformationMethod(null);;
                }
            }
        });
    }
}
