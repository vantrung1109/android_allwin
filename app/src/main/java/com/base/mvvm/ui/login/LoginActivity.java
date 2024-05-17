package com.base.mvvm.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.base.mvvm.BR;
import com.base.mvvm.R;
import com.base.mvvm.databinding.ActivityLoginBinding;
import com.base.mvvm.di.component.ActivityComponent;
import com.base.mvvm.ui.base.BaseActivity;
import com.base.mvvm.ui.main.MainActivity;
import com.base.mvvm.utils.LogService;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewBinding.setLoginForm(viewModel.loginForm);


        viewModel.doCheckOnStartup(new LoginCallback(){

            @Override
            public void doError(Throwable error) {
                //do later
            }

            @Override
            public void doSuccess() {
                navigationToMain();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginButtonLogin:
                doLogin();
                break;
            case R.id.loginForgotPwd:
                break;
            default:
                break;
        }
    }

    private void doLogin(){
        viewModel.doLogin(new LoginCallback() {
            @Override
            public void doError(Throwable error) {
                error.printStackTrace();
                LogService.e(error);
            }

            @Override
            public void doSuccess() {
                navigationToMain();
            }
        });

    }

    private void navigationToMain(){
        Intent it = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(it);
        finish();
    }
}