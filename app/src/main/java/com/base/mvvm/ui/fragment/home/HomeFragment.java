package com.base.mvvm.ui.fragment.home;



import com.base.mvvm.BR;
import com.base.mvvm.R;

import com.base.mvvm.databinding.FragmentHomeBinding;
import com.base.mvvm.di.component.FragmentComponent;
import com.base.mvvm.ui.base.BaseFragment;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>{


    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void performDataBinding() {

    }

    @Override
    protected void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }
}