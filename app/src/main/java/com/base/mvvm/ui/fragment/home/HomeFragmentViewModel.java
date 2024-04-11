package com.base.mvvm.ui.fragment.home;

import androidx.databinding.ObservableField;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.data.Repository;
import com.base.mvvm.ui.base.BaseFragmentViewModel;

public class HomeFragmentViewModel extends BaseFragmentViewModel {

    ObservableField<Boolean> isFocusEditText = new ObservableField<>();
    public HomeFragmentViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }

    public void onFocusEditText(boolean isFocus) {
        isFocusEditText.set(!isFocus);
    }

    public void onBackClick() {
        application.getCurrentActivity().finish();
    }
}
