package com.base.mvvm.di.module;

import android.content.Context;

import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.ViewModelProviderFactory;
import com.base.mvvm.data.Repository;
import com.base.mvvm.di.scope.ActivityScope;
import com.base.mvvm.ui.base.BaseActivity;
import com.base.mvvm.ui.home.HomeViewModel;
import com.base.mvvm.ui.login.LoginViewModel;
import com.base.mvvm.ui.main.MainViewModel;
import com.base.mvvm.ui.signin.SignInViewModel;
import com.base.mvvm.ui.signup.SignUpViewModel;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private BaseActivity<?, ?> activity;
    public ActivityModule(BaseActivity<?, ?> activity) {
        this.activity = activity;
    }

    @Named("access_token")
    @Provides
    @ActivityScope
    String provideToken(Repository repository){
        return repository.getToken();
    }

    @Provides
    @ActivityScope
    LoginViewModel provideLoginViewModel(Repository repository, Context application) {
        Supplier<LoginViewModel> supplier = () -> new LoginViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<LoginViewModel> factory = new ViewModelProviderFactory<>(LoginViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(LoginViewModel.class);
    }

    @Provides
    @ActivityScope
    MainViewModel provideMainViewModel(Repository repository, Context application) {
        Supplier<MainViewModel> supplier = () -> new MainViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<MainViewModel> factory = new ViewModelProviderFactory<>(MainViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(MainViewModel.class);
    }

    @Provides
    @ActivityScope
    SignInViewModel provideSignInViewModel(Repository repository, Context application) {
        Supplier<SignInViewModel> supplier = () -> new SignInViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<SignInViewModel> factory = new ViewModelProviderFactory<>(SignInViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(SignInViewModel.class);
    }

    @Provides
    @ActivityScope
    SignUpViewModel provideSignUpViewModel(Repository repository, Context application) {
        Supplier<SignUpViewModel> supplier = () -> new SignUpViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<SignUpViewModel> factory = new ViewModelProviderFactory<>(SignUpViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(SignUpViewModel.class);
    }

    @Provides
    @ActivityScope
    HomeViewModel provideHomeViewModel(Repository repository, Context application) {
        Supplier<HomeViewModel> supplier = () -> new HomeViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<HomeViewModel> factory = new ViewModelProviderFactory<>(HomeViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(HomeViewModel.class);
    }
}
