package com.base.mvvm.data.model.api.response.driver;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverProfileResponse implements Serializable {
    private Long id;
    private String address;
    private String avatar;
    private Integer averageRating;
    private District district;
    private String fullName;
    private String phone;
    private Province province;
    private Integer status;
    private Ward ward;
}
