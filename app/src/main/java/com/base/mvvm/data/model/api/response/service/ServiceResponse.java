package com.base.mvvm.data.model.api.response.service;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse implements Serializable {
    private CategoryResponse category;
    private String description;
    private Long id;
    private String image;
    private Integer kind;
    private String name;
    private String price;
    private String size;
    private String weight;


}
