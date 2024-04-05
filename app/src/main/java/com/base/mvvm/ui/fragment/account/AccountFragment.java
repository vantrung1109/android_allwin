package com.base.mvvm.ui.fragment.account;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.base.mvvm.BR;
import com.base.mvvm.R;
import com.base.mvvm.databinding.FragmentAccountBinding;
import com.base.mvvm.di.component.FragmentComponent;
import com.base.mvvm.ui.base.BaseFragment;
import com.base.mvvm.ui.home.HomeActivity;


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


}