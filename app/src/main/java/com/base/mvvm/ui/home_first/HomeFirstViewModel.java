package com.base.mvvm.ui.home_first;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.data.Repository;
import com.base.mvvm.ui.base.BaseViewModel;

public class HomeFirstViewModel extends BaseViewModel {
    public HomeFirstViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }
    public boolean isHaveToken(){
        if (repository.getToken() == null || repository.getToken().isEmpty())
            return false;
        return false;
    }
    }

