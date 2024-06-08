package com.base.mvvm.data.model.api.response.map.direction;

import com.base.mvvm.data.model.api.response.map.distance.Distance;
import com.base.mvvm.data.model.api.response.map.distance.Duration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Leg {
    private Distance distance;
    private Duration duration;
    private String end_address;
    private EndLocation end_location;
    private String start_address;
    private StartLocation start_location;
    private Step[] steps;
}
