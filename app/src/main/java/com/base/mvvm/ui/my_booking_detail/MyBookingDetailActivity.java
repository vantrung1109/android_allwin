package com.base.mvvm.ui.my_booking_detail;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.base.mvvm.R;
import com.base.mvvm.data.model.api.response.booking.MyBookingResponse;
import com.base.mvvm.databinding.ActivityBookingDetailBinding;
import com.base.mvvm.di.component.ActivityComponent;
import com.base.mvvm.ui.base.BaseActivity;

import java.io.Serializable;

import eu.davidea.flexibleadapter.databinding.BR;

public class MyBookingDetailActivity extends BaseActivity<ActivityBookingDetailBinding, MyBookingDetailViewModel>{
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
            viewBinding.tvMoney.setText(myBookingResponse.getMoney().toString());
            viewBinding.tvDiscount.setText(myBookingResponse.getPromotionMoney().toString());
            viewBinding.tvCreateDate.setText(myBookingResponse.getCreatedDate());
            viewBinding.tvPickupAddress.setText(myBookingResponse.getPickupAddress());
            viewBinding.tvDestinationAddress.setText(myBookingResponse.getDestinationAddress());
            viewBinding.tvCode.setText("Mã:" + myBookingResponse.getCode());

//            if (myBookingResponse != null) {
//                viewModel.myBooking.get().setDriver(myBookingResponse.getDriver());
//                viewModel.myBooking.get().setDriverVehicle(myBookingResponse.getDriverVehicle());
//
//                viewModel.myBooking.get().setMoney(myBookingResponse.getMoney());
//                viewModel.myBooking.get().setPromotionMoney(myBookingResponse.getPromotionMoney());
//                viewModel.myBooking.get().setCreatedDate(myBookingResponse.getCreatedDate());
//                viewModel.myBooking.get().setCode(myBookingResponse.getCode());
//
//                viewModel.myBooking.get().setPickupAddress(myBookingResponse.getPickupAddress());
//                viewModel.myBooking.get().setDestinationAddress(myBookingResponse.getDestinationAddress());
            } else {

            }
        }
    }
