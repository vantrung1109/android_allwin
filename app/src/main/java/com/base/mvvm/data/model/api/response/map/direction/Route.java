package com.base.mvvm.data.model.api.response.map.direction;

import com.base.mvvm.data.model.api.response.map.address_by_placeid.Bounds;
import com.google.android.gms.maps.model.Polyline;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    private Bounds bounds;
    private Leg[] legs;
    private Polyline overview_polyline;
    private String summary;
    private String[] warnings;
    private Integer[] waypoint_order;

}
