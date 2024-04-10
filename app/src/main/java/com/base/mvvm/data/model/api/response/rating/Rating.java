package com.base.mvvm.data.model.api.response.rating;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating implements Serializable {
    Long id;
    String message;
    Integer star;
    String createdDate;

}
