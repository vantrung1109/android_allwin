package com.base.mvvm.data.model.api.response.map.direction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectionResponse {
    private GeoCodedWayPoint[] geocoded_waypoints;
    private Route[] routes;
    private String status;
}
