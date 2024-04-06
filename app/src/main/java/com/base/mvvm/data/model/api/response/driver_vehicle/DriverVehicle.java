package com.base.mvvm.data.model.api.response.driver_vehicle;

import com.base.mvvm.data.model.api.response.driver.DriverProfileResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverVehicle {
    private Long id;
    private Brand brand;
    private String CreatedDate;
    private DriverProfileResponse driver;
    private String image;
    private String licenseNo;
    private String modifiedDate;
    private String name;
    private String plate;
    private Integer status;
}
