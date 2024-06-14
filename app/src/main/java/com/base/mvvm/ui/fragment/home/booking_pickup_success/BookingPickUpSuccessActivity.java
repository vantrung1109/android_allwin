package com.base.mvvm.ui.fragment.home.booking_pickup_success;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.base.mvvm.BR;
import com.base.mvvm.R;
import com.base.mvvm.data.model.api.response.current_booking.CurrentBookingResponse;
import com.base.mvvm.databinding.ActivityBookingDoneBinding;
import com.base.mvvm.databinding.ActivityBookingPickUpSuccessBinding;
import com.base.mvvm.di.component.ActivityComponent;
import com.base.mvvm.ui.base.BaseActivity;
import com.base.mvvm.ui.fragment.HomeCallBack;
import com.base.mvvm.utils.DisplayUtils;

import java.util.List;

import eu.davidea.flexibleadapter.items.IFlexible;

public class BookingPickUpSuccessActivity extends BaseActivity<ActivityBookingPickUpSuccessBinding, BookingPickUpSuccessViewModel>
    implements HomeCallBack
{

    //MutableLiveData<CurrentBookingResponse> currentBookingResponse = new MutableLiveData<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_booking_pick_up_success;
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

        viewModel.setListenerCallBack(this);
        viewModel.getMyCurrentBooking();


    }

    @Override
    public void doSuccessGetData(Object data) {

            List<CurrentBookingResponse> currentBookingResponses = (List<CurrentBookingResponse>) data;
            CurrentBookingResponse currentBookingResponse = currentBookingResponses.get(0);
            viewBinding.tvDriverName.setText(currentBookingResponse.getDriver().getFullName());
            viewBinding.tvDriverVehicle.setText(currentBookingResponse.getDriverVehicle().getName());
            viewBinding.tvPlate.setText(currentBookingResponse.getDriverVehicle().getPlate());
            viewBinding.tvRating.setText(String.valueOf(currentBookingResponse.getAverageRating()));
            viewBinding.tvMoney.setText(DisplayUtils.custom_money(currentBookingResponse.getMoney()));
            viewBinding.tvDiscount.setText(DisplayUtils.custom_money(currentBookingResponse.getPromotionMoney()));
            viewBinding.tvSumMoney.setText(DisplayUtils.custom_money(currentBookingResponse.getMoney() - currentBookingResponse.getPromotionMoney()));
            viewBinding.tvPickupAddress.setText(currentBookingResponse.getPickupAddress());
            viewBinding.tvDestinationAddress.setText(currentBookingResponse.getDestinationAddress());
            viewBinding.tvCode.setText(currentBookingResponse.getCode());
            viewBinding.tvCreateDate.setText(currentBookingResponse.getCreatedDate());
        
    }

    @Override
    public void doSuccessGetData(List<Object> data) {

    }

    @Override
    public void doSuccess() {

    }

    @Override
    public void doError() {

    }
}
