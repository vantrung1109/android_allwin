package com.base.mvvm.ui.signup;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.base.mvvm.BR;
import com.base.mvvm.R;
import com.base.mvvm.databinding.ActivitySigninBinding;
import com.base.mvvm.databinding.ActivitySignupBinding;
import com.base.mvvm.di.component.ActivityComponent;
import com.base.mvvm.ui.base.BaseActivity;
import com.base.mvvm.ui.signin.SignInViewModel;

public class SignUpActivity extends BaseActivity<ActivitySignupBinding, SignUpViewModel> {
    ActivitySignupBinding mActivitySignupBinding;
    @Override
    public int getLayoutId() {
        return R.layout.activity_signup;
    }
    @Override
    public int getBindingVariable() {
        return BR.vm;
    }
    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

}
