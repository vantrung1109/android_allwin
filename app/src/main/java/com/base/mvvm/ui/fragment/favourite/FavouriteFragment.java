package com.base.mvvm.ui.fragment.favourite;

import android.os.Bundle;

import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.mvvm.R;
import com.base.mvvm.databinding.FragmentFavouriteBinding;
import com.base.mvvm.di.component.FragmentComponent;
import com.base.mvvm.ui.base.BaseFragment;


public class FavouriteFragment extends BaseFragment<FragmentFavouriteBinding, FavouriteFragmentViewModel> {


    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_favourite;
    }

    @Override
    protected void performDataBinding() {

    }

    @Override
    protected void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }
}