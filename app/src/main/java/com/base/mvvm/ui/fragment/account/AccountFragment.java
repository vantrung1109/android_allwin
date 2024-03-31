package com.base.mvvm.ui.fragment.account;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.mvvm.BR;
import com.base.mvvm.R;
import com.base.mvvm.databinding.FragmentAccountBinding;
import com.base.mvvm.di.component.FragmentComponent;
import com.base.mvvm.ui.base.BaseFragment;


public class AccountFragment extends BaseFragment<FragmentAccountBinding, AccountFragmentViewModel> {


    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_account;
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
        return super.onCreateView(inflater, container, savedInstanceState);

        // goi api
    }
}