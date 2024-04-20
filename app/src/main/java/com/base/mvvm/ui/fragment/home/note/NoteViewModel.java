package com.base.mvvm.ui.fragment.home.note;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.data.Repository;
import com.base.mvvm.ui.base.BaseViewModel;

public class NoteViewModel extends BaseViewModel {
    public NoteViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }

    public void onBack(){
        application.getCurrentActivity().finish();
    }

}
