package com.base.mvvm.ui.fragment.home.booking_done;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.base.mvvm.R;
import com.base.mvvm.databinding.ActivityBookingDoneBinding;
import com.base.mvvm.di.component.ActivityComponent;
import com.base.mvvm.ui.base.BaseActivity;
import com.base.mvvm.ui.main.MainActivity;

import eu.davidea.flexibleadapter.databinding.BR;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding.btnAccept.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }
}
