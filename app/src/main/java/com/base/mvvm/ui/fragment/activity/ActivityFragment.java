package com.base.mvvm.ui.fragment.activity;

import com.base.mvvm.BR;
import com.base.mvvm.R;

import com.base.mvvm.databinding.FragmentActivityBinding;
import com.base.mvvm.di.component.FragmentComponent;
import com.base.mvvm.ui.base.BaseFragment;
import com.base.mvvm.ui.fragment.home.HomeFragmentViewModel;


public class ActivityFragment extends BaseFragment<FragmentActivityBinding, HomeFragmentViewModel>{
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
}