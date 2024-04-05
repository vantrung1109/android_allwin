package com.base.mvvm.ui.fragment.account;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

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

    public void doSignout(){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        Intent intent = new Intent(getActivity(), HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        clearToken();
                        getActivity().startActivity(intent);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Bạn muốn đăng xuất?").setPositiveButton(getContext().getString(R.string.confirm), dialogClickListener)
                .setNegativeButton(getContext().getString(R.string.cancel), dialogClickListener).show();
    }

}