package com.base.mvvm.ui.fragment.home;



import androidx.recyclerview.widget.LinearLayoutManager;

import com.base.mvvm.BR;
import com.base.mvvm.R;

import com.base.mvvm.data.service.DatabaseService;
import com.base.mvvm.databinding.FragmentHomeBinding;
import com.base.mvvm.di.component.FragmentComponent;
import com.base.mvvm.ui.base.BaseFragment;

import eu.davidea.flexibleadapter.FlexibleAdapter;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>{

    FlexibleAdapter mFlexibleAdapterTitleAddressSave, mFlexibleAdapterAddressSaveItem;

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

        mFlexibleAdapterTitleAddressSave = new FlexibleAdapter(DatabaseService.getInstance().getTitleAddressSave(), this.getActivity());
        binding.rcvItemTitleSaveAddress.setAdapter(mFlexibleAdapterTitleAddressSave);
        binding.rcvItemTitleSaveAddress.setLayoutManager(
                new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false)
        );

        mFlexibleAdapterAddressSaveItem = new FlexibleAdapter(DatabaseService.getInstance().getAddressSaveItems(), this.getActivity());


    }

    @Override
    protected void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }
}