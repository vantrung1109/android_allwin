package com.base.mvvm.ui.signup;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.base.mvvm.BR;
import com.base.mvvm.R;
import com.base.mvvm.databinding.ActivitySigninBinding;
import com.base.mvvm.databinding.ActivitySignupBinding;
import com.base.mvvm.di.component.ActivityComponent;
import com.base.mvvm.ui.base.BaseActivity;
import com.base.mvvm.ui.signin.SignInViewModel;

public class SignUpActivity extends BaseActivity<ActivitySignupBinding, SignUpViewModel> {
    ActivitySignupBinding mActivitySignupBinding;
    @Override
    public int getLayoutId() {
        return R.layout.activity_signup;
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
        mActivitySignupBinding = ActivitySignupBinding.inflate(getLayoutInflater());

//        mActivitySignupBinding.btnSignin.setOnClickListener(v -> {
//            if (!checkRequiredField()) return;
//        });

        setContentView(mActivitySignupBinding.getRoot());
    }

//    private boolean checkRequiredField() {
//
//        TextWatcher textWatcher = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {}
//            @Override
//            public void afterTextChanged(Editable s) {
//                boolean isNameAccountEmpty = mActivitySignupBinding.editPhone.getText().toString().isEmpty();
//                boolean isPasswordEmpty = mActivitySignupBinding.editPassword.getText().toString().isEmpty();
//                if (isNameAccountEmpty || isPasswordEmpty) {
//                    mActivitySignupBinding.btnSignin.setEnabled(true);
//                    mActivitySignupBinding.btnSignin.setBackground(getResources().getDrawable(R.drawable.btn_custom_enable, null));
//                }
//                if (isNameAccountEmpty && isPasswordEmpty) {
//                    mActivitySignupBinding.btnSignin.setEnabled(false);
//                    mActivitySignupBinding.btnSignin.setBackground(getResources().getDrawable(R.drawable.btn_custom_disable, null));
//                }
//            }
//        };
//        mActivitySignupBinding.editPhone.addTextChangedListener(textWatcher);
//        mActivitySignupBinding.editPassword.addTextChangedListener(textWatcher);
//
//        boolean isNotHavePassWord = mActivitySignupBinding.editPassword.getText().toString().isEmpty();
//        if (isNotHavePassWord){
//            mActivitySignupBinding.editPassword.setBackground(getResources().getDrawable(R.drawable.edit_txt_custom_error, null));
//            Toast.makeText(SignUpActivity.this, "Bạn chưa nhập mật khẩu", Toast.LENGTH_SHORT).show();
//            return true;
//        } else {
//            mActivitySignupBinding.editPassword.setBackground(getResources().getDrawable(R.drawable.edit_txt_custom, null));
//        }
//
//        boolean isNotHaveUserName = mActivitySignupBinding.editPhone.getText().toString().isEmpty();
//        if (isNotHaveUserName){
//            mActivitySignupBinding.editPhone.setBackground(getResources().getDrawable(R.drawable.edit_txt_custom_error, null));
//            Toast.makeText(SignUpActivity.this, "Bạn chưa nhập tên tài khoản", Toast.LENGTH_SHORT).show();
//            return true;
//        } else {
//            mActivitySignupBinding.editPhone.setBackground(getResources().getDrawable(R.drawable.edit_txt_custom, null));
//        }
//        return false;
//    }
}
