package com.base.mvvm.data.model.api.response.booking.booking_create;

import com.base.mvvm.data.model.api.response.booking.BookingDetail;
import com.base.mvvm.data.model.api.response.customer.CustomerProfileResponse;
import com.base.mvvm.data.model.api.response.driver.DriverProfileResponse;
import com.base.mvvm.data.model.api.response.service.ServiceResponse;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingCreateResponse {
    Long id;
    Integer status;
    String modifiedDate;
    String createdDate;
    CustomerProfileResponse customer;
    ServiceResponse service;
    String code;
    Integer state;
    Integer paymentKind;
    String pickupAddress;
    Double pickupLat;
    Double pickupLong;
    String destinationAddress;
    Double destinationLat;
    Double destinationLong;
    Double distance;
    Double money;
    Double promotionMoney;
    List<BookingDetail> bookingDetails;
    DriverProfileResponse driver;
}
