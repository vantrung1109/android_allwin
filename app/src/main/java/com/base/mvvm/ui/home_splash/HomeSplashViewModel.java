package com.base.mvvm.ui.home_splash;

import android.util.Log;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.data.Repository;
import com.base.mvvm.ui.base.BaseViewModel;

import java.util.Objects;

public class HomeSplashViewModel extends BaseViewModel {
    public HomeSplashViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }
    public boolean isHaveToken(){
        Log.e("token",repository.getToken());
        if (Objects.equals(repository.getToken(), "NULL") || repository.getToken().isEmpty())
            return false;
        return true;
    }

}

