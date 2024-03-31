package com.base.mvvm.di.component;

import com.base.mvvm.di.module.ActivityModule;
import com.base.mvvm.di.scope.ActivityScope;
import com.base.mvvm.ui.home.HomeActivity;
import com.base.mvvm.ui.login.LoginActivity;
import com.base.mvvm.ui.main.MainActivity;
import com.base.mvvm.ui.signin.SignInActivity;
import com.base.mvvm.ui.signup.SignUpActivity;

import dagger.Component;

@ActivityScope
@Component(modules = {ActivityModule.class}, dependencies = AppComponent.class)
public interface ActivityComponent {

    void inject(LoginActivity activity);

    void inject(MainActivity activity);
    void inject(SignInActivity activity);
    void inject(SignUpActivity activity);
    void inject(HomeActivity activity);
}

