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
public class Parent implements Serializable {
    private String createdBy;
    private String createdDate;
    private Long id;
    private Integer kind;
    private String modifiedBy;
    private String modifiedDate;
    private String name;
    private String postCode;
    private Integer status;

}
