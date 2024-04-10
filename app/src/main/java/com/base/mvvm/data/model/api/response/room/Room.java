package com.base.mvvm.data.model.api.response.room;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room implements  Serializable {
    Long id;
    String timeStart;
    String timeEnd;


}

