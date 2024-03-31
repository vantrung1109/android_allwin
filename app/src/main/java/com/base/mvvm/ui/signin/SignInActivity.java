package com.base.mvvm.ui.signin;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.base.mvvm.BR;
import com.base.mvvm.R;
import com.base.mvvm.databinding.ActivitySigninBinding;
import com.base.mvvm.di.component.ActivityComponent;
import com.base.mvvm.ui.base.BaseActivity;

public class SignInActivity extends BaseActivity<ActivitySigninBinding, SignInViewModel> {
    ActivitySigninBinding mActivitySigninBinding;
    @Override
    public int getLayoutId() {
        return R.layout.activity_signin;
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
        mActivitySigninBinding = ActivitySigninBinding.inflate(getLayoutInflater());

        mActivitySigninBinding.btnSignin.setOnClickListener(v -> {
            if (!checkRequiredField()) return;
        });

        setContentView(mActivitySigninBinding.getRoot());
    }

    private boolean checkRequiredField() {

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                boolean isNameAccountEmpty = mActivitySigninBinding.editPhone.getText().toString().isEmpty();
                boolean isPasswordEmpty = mActivitySigninBinding.editPassword.getText().toString().isEmpty();
                if (isNameAccountEmpty || isPasswordEmpty) {
                    mActivitySigninBinding.btnSignin.setEnabled(true);
                    mActivitySigninBinding.btnSignin.setBackground(getResources().getDrawable(R.drawable.btn_custom_enable, null));
                }
                if (isNameAccountEmpty && isPasswordEmpty) {
                    mActivitySigninBinding.btnSignin.setEnabled(false);
                    mActivitySigninBinding.btnSignin.setBackground(getResources().getDrawable(R.drawable.btn_custom_disable, null));
                }
            }
        };
        mActivitySigninBinding.editPhone.addTextChangedListener(textWatcher);
        mActivitySigninBinding.editPassword.addTextChangedListener(textWatcher);

        boolean isNotHavePassWord = mActivitySigninBinding.editPassword.getText().toString().isEmpty();
        if (isNotHavePassWord){
            mActivitySigninBinding.editPassword.setBackground(getResources().getDrawable(R.drawable.edit_txt_custom_error, null));
            Toast.makeText(SignInActivity.this, "Bạn chưa nhập mật khẩu", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            mActivitySigninBinding.editPassword.setBackground(getResources().getDrawable(R.drawable.edit_txt_custom, null));
        }

        boolean isNotHaveUserName = mActivitySigninBinding.editPhone.getText().toString().isEmpty();
        if (isNotHaveUserName){
            mActivitySigninBinding.editPhone.setBackground(getResources().getDrawable(R.drawable.edit_txt_custom_error, null));
            Toast.makeText(SignInActivity.this, "Bạn chưa nhập tên tài khoản", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            mActivitySigninBinding.editPhone.setBackground(getResources().getDrawable(R.drawable.edit_txt_custom, null));
        }
        return false;
    }
}
