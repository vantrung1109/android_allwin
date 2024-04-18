package com.base.mvvm.ui.my_booking_detail;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.base.mvvm.R;
import com.base.mvvm.data.model.api.response.booking.MyBookingResponse;
import com.base.mvvm.databinding.ActivityBookingDetailBinding;
import com.base.mvvm.di.component.ActivityComponent;
import com.base.mvvm.ui.base.BaseActivity;
import com.base.mvvm.utils.DisplayUtils;

import java.io.Serializable;

import eu.davidea.flexibleadapter.databinding.BR;

public class MyBookingDetailActivity extends BaseActivity<ActivityBookingDetailBinding, MyBookingDetailViewModel> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_booking_detail;
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

        // Nhận dữ liệu từ Intent
        Bundle bundle = getIntent().getExtras();
        Log.e("MyBookingDetailActivity", "onCreate: " + bundle);
        if (bundle != null) {
            // Lấy đối tượng Parcelable từ Bundle
            MyBookingResponse myBookingResponse = (MyBookingResponse) bundle.getSerializable("myBookingResponse");
//            Log.e("MyBooking", "MyBookingDetailActivity: " + myBookingResponse.getDriver());
//            Log.e("MyBooking", "MyBookingDetailActivity: " + myBookingResponse.getDriverVehicle());
//            Log.e("MyBooking", "MyBookingDetailActivity: " + myBookingResponse.getCustomer());
//            Log.e("MyBooking", "MyBookingDetailActivity: " + myBookingResponse.getBookingDetails());
            viewBinding.tvDriverName.setText(myBookingResponse.getDriver().getFullName());
            //viewBinding.imgAvatar.setImageResource(myBookingResponse.getDriver().getAvatar());
            viewBinding.tvDriverVehicle.setText(myBookingResponse.getDriverVehicle().getName());
            viewBinding.tvPlate.setText(myBookingResponse.getDriverVehicle().getPlate());
            viewBinding.tvMoney.setText(DisplayUtils.custom_money(myBookingResponse.getMoney()));
            viewBinding.tvDiscount.setText(DisplayUtils.custom_money(myBookingResponse.getPromotionMoney()));
            viewBinding.tvCreateDate.setText(myBookingResponse.getCreatedDate());
            viewBinding.tvPickupAddress.setText(myBookingResponse.getPickupAddress());
            viewBinding.tvDestinationAddress.setText(myBookingResponse.getDestinationAddress());
            viewBinding.tvCode.setText("Mã: " + myBookingResponse.getCode());
            viewBinding.tvSumMoney.setText(DisplayUtils.custom_money(myBookingResponse.getMoney() - myBookingResponse.getPromotionMoney()));

            if (myBookingResponse.getState() == 300) {
                viewBinding.tvState.setText("Đã hoàn thành");
                viewBinding.tvState.setBackground(getResources().getDrawable(R.drawable.bg_state_completed_booking, null));
                viewBinding.tvState.setTextColor(getResources().getColor(R.color.completed_booking, null));
            } else if (myBookingResponse.getState() == -100) {
                viewBinding.tvState.setText("Đã hủy");
                viewBinding.tvState.setBackground(getResources().getDrawable(R.drawable.bg_state_cancel_booking, null));
                viewBinding.tvState.setTextColor(getResources().getColor(R.color.text_state_cancel, null));
            } else {
                viewBinding.tvState.setText("Đang xử lý");
                viewBinding.tvState.setBackground(getResources().getDrawable(R.drawable.bg_state_processing_booking, null));
                viewBinding.tvState.setTextColor(getResources().getColor(R.color.processing_booking, null));
            }

        }
    }
}
