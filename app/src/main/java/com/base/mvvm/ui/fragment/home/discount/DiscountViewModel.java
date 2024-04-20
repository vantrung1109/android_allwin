package com.base.mvvm.ui.fragment.home.discount;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.data.Repository;
import com.base.mvvm.ui.base.BaseViewModel;

public class DiscountViewModel extends BaseViewModel {
    public DiscountViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }

    public void onBack(){
        application.getCurrentActivity().finish();
    }

}
