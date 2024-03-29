package com.base.mvvm.ui.signin;

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
}
