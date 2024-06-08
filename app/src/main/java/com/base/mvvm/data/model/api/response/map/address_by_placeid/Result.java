package com.base.mvvm.data.model.api.response.map.address_by_placeid;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private List<AddressComponent> address_components;
    private String formatted_address;
    private Geometry geometry;
    private String place_id;
    private List<String> types;
}
