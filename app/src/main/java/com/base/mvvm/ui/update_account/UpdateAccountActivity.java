package com.base.mvvm.ui.update_account;

import android.os.Bundle;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;

import androidx.annotation.Nullable;
import androidx.databinding.Observable;

import com.base.mvvm.R;
import com.base.mvvm.databinding.ActivityUpdateAccountBinding;
import com.base.mvvm.di.component.ActivityComponent;
import com.base.mvvm.ui.base.BaseActivity;

import eu.davidea.flexibleadapter.databinding.BR;

public class UpdateAccountActivity extends BaseActivity<ActivityUpdateAccountBinding, UpdateAccountViewModel> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_update_account;
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

        viewModel.isShowPassWord.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if(!viewModel.isShowPassWord.get()){
                    viewBinding.editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else {
                    viewBinding.editPassword.setTransformationMethod(null);;
                }
            }
        });
//        viewBinding.editPassword.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() < 1 && viewBinding.editName.getText().toString().isEmpty() && viewBinding.editEmail.getText().toString().isEmpty()){
//                    viewBinding.btnUpdate.setEnabled(false);
//                    viewBinding.btnUpdate.setBackground(getResources().getDrawable(R.drawable.btn_custom_disable, null));
//                } else {
//                    viewBinding.btnUpdate.setEnabled(true);
//                    viewBinding.btnUpdate.setBackground(getResources().getDrawable(R.drawable.btn_custom_enable, null));
//                }
//            }
//            @Override
//            public void afterTextChanged(android.text.Editable s) {
//            }
//        });
    }
}
