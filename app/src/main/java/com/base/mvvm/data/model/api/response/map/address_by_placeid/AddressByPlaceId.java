package com.base.mvvm.data.model.api.response.map.address_by_placeid;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressByPlaceId {
    private List<Result>  results;
    private String status;
}
