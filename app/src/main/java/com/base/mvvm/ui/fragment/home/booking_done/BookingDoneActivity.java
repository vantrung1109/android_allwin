package com.base.mvvm.ui.fragment.home.booking_done;

import com.base.mvvm.BR;
import com.base.mvvm.R;
import com.base.mvvm.databinding.ActivityBookingDoneBinding;
import com.base.mvvm.di.component.ActivityComponent;
import com.base.mvvm.ui.base.BaseActivity;

public class BookingDoneActivity extends BaseActivity<ActivityBookingDoneBinding, BookingDoneViewModel> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_booking_done;
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
