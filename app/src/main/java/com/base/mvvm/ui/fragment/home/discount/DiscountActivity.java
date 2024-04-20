package com.base.mvvm.ui.fragment.home.discount;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.base.mvvm.R;
import com.base.mvvm.data.service.DatabaseService;
import com.base.mvvm.databinding.ActivityDiscountBinding;
import com.base.mvvm.databinding.ActivityPaymentMethodBinding;
import com.base.mvvm.di.component.ActivityComponent;
import com.base.mvvm.ui.base.BaseActivity;
import com.base.mvvm.ui.fragment.home.payment_method.PaymentMethodViewModel;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.databinding.BR;

public class DiscountActivity extends BaseActivity<ActivityDiscountBinding, DiscountViewModel> {


    FlexibleAdapter mFlexibleAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_discount;
    }

    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFlexibleAdapter = new FlexibleAdapter<>(DatabaseService.getInstance().getDiscountItems(), this);
        viewBinding.rcvDiscount.setLayoutManager(new LinearLayoutManager(this));
        viewBinding.rcvDiscount.setAdapter(mFlexibleAdapter);
    }

}
