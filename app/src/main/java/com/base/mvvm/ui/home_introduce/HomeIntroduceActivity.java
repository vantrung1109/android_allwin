package com.base.mvvm.ui.home_introduce;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.base.mvvm.R;
import com.base.mvvm.databinding.ActivityHomeIntroduceBinding;
import com.base.mvvm.databinding.ActivitySplashHomeBinding;
import com.base.mvvm.di.component.ActivityComponent;
import com.base.mvvm.ui.base.BaseActivity;
import com.base.mvvm.ui.home.HomeActivity;
import com.base.mvvm.ui.main.MainActivity;

import eu.davidea.flexibleadapter.databinding.BR;

public class HomeIntroduceActivity extends BaseActivity<ActivityHomeIntroduceBinding, HomeIntroduceViewModel> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_home_introduce;
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
