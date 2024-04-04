package com.base.mvvm.ui.home_first;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.base.mvvm.R;
import com.base.mvvm.databinding.ActivityHomeFirstBinding;
import com.base.mvvm.di.component.ActivityComponent;
import com.base.mvvm.ui.base.BaseActivity;
import com.base.mvvm.ui.home.HomeActivity;
import com.base.mvvm.ui.main.MainActivity;

import eu.davidea.flexibleadapter.databinding.BR;

public class HomeFirstActivity extends BaseActivity<ActivityHomeFirstBinding, HomeFirstViewModel> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_home_first;
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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (viewModel.isHaveToken()){
                    intent = new Intent(HomeFirstActivity.this, MainActivity.class);
                }
                else {
                    intent = new Intent(HomeFirstActivity.this, HomeActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, 2500);
    }
}
