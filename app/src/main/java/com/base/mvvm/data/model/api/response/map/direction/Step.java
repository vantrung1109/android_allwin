package com.base.mvvm.data.model.api.response.map.direction;

import com.base.mvvm.data.model.api.response.map.distance.Distance;
import com.base.mvvm.data.model.api.response.map.distance.Duration;
import com.google.android.gms.maps.model.Polyline;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Step {
    private Distance distance;
    private Duration duration;
    private EndLocation end_location;
    private String html_instructions;
    private String maneuver;
    private Polyline polyline;
    private StartLocation start_location;
    private String travel_mode;
}
