package com.base.mvvm.ui.fragment.home.cancel_trip.cancel_success;

import com.base.mvvm.R;
import com.base.mvvm.databinding.ActivityCancelTripSuccessBinding;
import com.base.mvvm.databinding.ActivityNoteBinding;
import com.base.mvvm.di.component.ActivityComponent;
import com.base.mvvm.ui.base.BaseActivity;

import eu.davidea.flexibleadapter.databinding.BR;

public class CancelTripSuccessActivity extends BaseActivity<ActivityCancelTripSuccessBinding, CancelTripSuccessViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_note;
    }

    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }


}
