package com.base.mvvm.ui.fragment.home.cancel_trip;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.data.Repository;
import com.base.mvvm.ui.base.BaseViewModel;

public class CancelTripViewModel extends BaseViewModel {
    public CancelTripViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }

    public void onBack(){
        application.getCurrentActivity().finish();
    }



}
