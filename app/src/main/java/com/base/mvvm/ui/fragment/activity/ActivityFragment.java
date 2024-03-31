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

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;


public class ActivityFragment extends BaseFragment<FragmentActivityBinding, ActivityFragmentViewModel>
        implements FlexibleAdapter.OnItemClickListener
{
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

    @Override
    public boolean onItemClick(View view, int i) {
        IFlexible flexibleItem = mFlexibleAdapterOption.getItem(i);
        if (flexibleItem instanceof Option) {
            Option option = (Option) flexibleItem;
            switch (option.getTitle()) {
                case "Đặt xe": {

                    mFlexibleAdapterVehicleOrder = new FlexibleAdapter<>(DatabaseService.getInstance().getVehicleOrdersList(), this);
                    mFragmentActivityBinding.rcvVehicleOrder.setAdapter(mFlexibleAdapterVehicleOrder);
                    mFragmentActivityBinding.rcvVehicleOrder.setLayoutManager(
                            new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    return true;

                }
                case "Giao hàng": {

                    return true;
                }
                case "Đi chợ": {

                    return true;
                }

            }
        }
        return false;
    }
}