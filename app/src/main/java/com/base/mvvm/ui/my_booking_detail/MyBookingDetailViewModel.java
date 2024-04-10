package com.base.mvvm.ui.my_booking_detail;

import androidx.databinding.ObservableField;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.data.Repository;
import com.base.mvvm.data.model.api.response.booking.MyBookingResponse;
import com.base.mvvm.data.model.api.response.customer.CustomerProfileResponse;
import com.base.mvvm.data.model.api.response.driver.DriverProfileResponse;
import com.base.mvvm.data.model.api.response.driver_vehicle.DriverVehicle;
import com.base.mvvm.data.model.api.response.rating.Rating;
import com.base.mvvm.data.model.api.response.room.Room;
import com.base.mvvm.ui.base.BaseViewModel;

import java.util.Observable;

public class MyBookingDetailViewModel extends BaseViewModel {
    public ObservableField<MyBookingResponse> myBooking = new ObservableField<>(new MyBookingResponse());

    public MyBookingDetailViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }
    public void onBack(){
        application.getCurrentActivity().finish();
    }


}
