package com.base.mvvm.data.model.api.response.driver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverProfileResponse {
    private String address;
    private String avatar;
    private Integer averageRating;
    private District district;
    private String fullName;
    private String id;
    private String phone;
    private Province province;
    private Integer status;
    private Ward ward;
}
