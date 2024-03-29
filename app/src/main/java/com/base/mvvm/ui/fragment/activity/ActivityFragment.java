package com.base.mvvm.ui.fragment.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.base.mvvm.BR;
import com.base.mvvm.R;

import com.base.mvvm.data.service.DatabaseService;
import com.base.mvvm.databinding.FragmentActivityBinding;
import com.base.mvvm.di.component.FragmentComponent;
import com.base.mvvm.ui.base.BaseFragment;
import com.base.mvvm.ui.fragment.home.HomeFragmentViewModel;
import com.base.mvvm.ui.model.Option;

import eu.davidea.flexibleadapter.FlexibleAdapter;


public class ActivityFragment extends BaseFragment<FragmentActivityBinding, ActivityFragmentViewModel>{
    FragmentActivityBinding mFragmentActivityBinding;
    FlexibleAdapter mFlexibleAdapterOption;
    FlexibleAdapter mFlexibleAdapterVehicleOrder;
    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_activity;
    }

    @Override
    protected void performDataBinding() {
    }
    @Override
    protected void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mFragmentActivityBinding = FragmentActivityBinding.inflate(inflater);
        mFlexibleAdapterOption = new FlexibleAdapter<>(DatabaseService.getInstance().getOptionsList(), this);
        mFragmentActivityBinding.rcvOption.setAdapter(mFlexibleAdapterOption);
        mFragmentActivityBinding.rcvOption.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        mFlexibleAdapterVehicleOrder = new FlexibleAdapter<>(DatabaseService.getInstance().getVehicleOrdersList(), this);
        mFragmentActivityBinding.rcvVehicleOrder.setAdapter(mFlexibleAdapterVehicleOrder);
        mFragmentActivityBinding.rcvVehicleOrder.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        return mFragmentActivityBinding.getRoot();
    }
}