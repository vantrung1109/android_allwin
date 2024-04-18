package com.base.mvvm.data.model.api.response.booking;

import android.os.Parcel;
import android.os.Parcelable;

import com.base.mvvm.data.model.api.response.customer.CustomerProfileResponse;
import com.base.mvvm.data.model.api.response.driver.DriverProfileResponse;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  BookingDetail implements Serializable {
    private Long id;
    private DriverProfileResponse driver;
    private CustomerProfileResponse customer;
    private Integer state;
    private String createdDate;

}
