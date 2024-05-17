package com.base.mvvm.data.model.api.address_by_placeid;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Geometry {
    private Location location;
    private String location_type;
    private Viewport viewport;
    private Bounds bounds;
}
