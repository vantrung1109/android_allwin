package com.base.mvvm.data.model.websocket;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingAccepted implements Serializable {
    private String driverId;
    private String driverLatitude;
    private String driverLongitude;
    private String codeBooking;
}
