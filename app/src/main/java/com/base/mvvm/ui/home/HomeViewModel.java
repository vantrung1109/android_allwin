package com.base.mvvm.ui.home;

import android.content.Intent;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.data.Repository;
import com.base.mvvm.ui.base.BaseViewModel;
import com.base.mvvm.ui.signin.SignInActivity;
import com.base.mvvm.ui.signup.SignUpActivity;

public class HomeViewModel extends BaseViewModel {
    public HomeViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }
    public void doSignin() {
        Intent intent = new Intent(application, SignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        application.startActivity(intent);
    }
    public void doSignup(){
        Intent intent = new Intent(application, SignUpActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        application.startActivity(intent);
    }
}
