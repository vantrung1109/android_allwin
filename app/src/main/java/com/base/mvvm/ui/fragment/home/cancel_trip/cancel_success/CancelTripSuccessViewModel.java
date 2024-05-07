package com.base.mvvm.ui.fragment.home.cancel_trip.cancel_success;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.data.Repository;
import com.base.mvvm.ui.base.BaseViewModel;

public class CancelTripSuccessViewModel extends BaseViewModel {
    public CancelTripSuccessViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }

    public void onBack(){
        application.getCurrentActivity().finish();
    }

}
