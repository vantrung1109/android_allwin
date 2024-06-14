package com.base.mvvm.data.model.api.response.current_booking;

import com.base.mvvm.data.model.api.response.booking.BookingDetail;
import com.base.mvvm.data.model.api.response.customer.CustomerProfileResponse;
import com.base.mvvm.data.model.api.response.driver.DriverProfileResponse;
import com.base.mvvm.data.model.api.response.driver_vehicle.DriverVehicle;
import com.base.mvvm.data.model.api.response.room.Room;
import com.base.mvvm.data.model.api.response.service.ServiceResponse;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentBookingResponse {
    String id;
    String status;
    String modifiedDate;
    String createdDate;
    DriverProfileResponse driver;
    CustomerProfileResponse customer;
    ServiceResponse service;
    Room room;
    DriverVehicle driverVehicle;
    Double averageRating;
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
}
