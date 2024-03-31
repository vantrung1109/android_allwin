package com.base.mvvm.ui.home;

import com.base.mvvm.BR;
import com.base.mvvm.R;
import com.base.mvvm.databinding.ActivityHomeBinding;
import com.base.mvvm.di.component.ActivityComponent;
import com.base.mvvm.ui.base.BaseActivity;

public class HomeActivity extends BaseActivity<ActivityHomeBinding, HomeViewModel> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
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