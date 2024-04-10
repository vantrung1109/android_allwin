package com.base.mvvm.data.model.api.response.customer;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerProfileResponse implements Serializable {
    private String avatar;
    private String email;
    private Long id;
    private String name;
    private String phone;
    private Integer status;

}
