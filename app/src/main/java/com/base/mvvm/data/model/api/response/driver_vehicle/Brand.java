package com.base.mvvm.data.model.api.response.driver_vehicle;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand implements Serializable {
    private Long id;
    private Integer kind;
    private String name;
    private Integer status;

}
