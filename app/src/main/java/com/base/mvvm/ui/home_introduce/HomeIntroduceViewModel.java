package com.base.mvvm.ui.home_introduce;

import android.content.Intent;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.data.Repository;
import com.base.mvvm.ui.base.BaseViewModel;
import com.base.mvvm.ui.home.HomeActivity;

public class HomeIntroduceViewModel extends BaseViewModel {
    public HomeIntroduceViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }
    public void doContinue() {
        Intent intent = new Intent(application, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        application.startActivity(intent);
    }
}

