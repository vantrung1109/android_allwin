package com.base.mvvm.ui.fragment.activity;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.data.Repository;
import com.base.mvvm.ui.base.BaseFragmentViewModel;

public class ActivityFragmentViewModel extends BaseFragmentViewModel {
    public ActivityFragmentViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }
    public void haha() {
        //do something
        System.out.println("ActivityFragmentViewModel");
    }
}
