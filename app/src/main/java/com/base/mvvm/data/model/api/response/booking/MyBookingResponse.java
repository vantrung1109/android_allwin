package com.base.mvvm.data.model.api.response.booking;

import android.media.Rating;

import androidx.room.Room;

import com.base.mvvm.data.model.api.response.customer.CustomerProfileResponse;
import com.base.mvvm.data.model.api.response.driver.DriverProfileResponse;
import com.base.mvvm.data.model.api.response.driver_vehicle.DriverVehicle;
import com.base.mvvm.data.model.api.response.service.ServiceResponse;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyBookingResponse {
    private Long id;
    private Integer averageRating;
    private List<BookingDetail> bookingDetails;
    private Integer codPrice;
    private String code;
    private String consigneeName;
    private String consigneePhone;
    private String createdDate;
    private CustomerProfileResponse customer;
    private String customerNote;
    private String deliveryImage;
    private String destinationAddress;
    private Double distance;
    private DriverProfileResponse driver;
    private DriverVehicle driverVehicle;
    private String idCod;
    private Double money;
    private String pickupAddress;
    private Double pickupImage;
    private Double promotionMoney;
    private Rating rating;
    private Double ratioShare;
    private Room room;
    private String senderName;
    private String senderPhone;
    private ServiceResponse service;
    private Integer state;
    private String status;
}
