package com.base.mvvm.data.model.api.response.map.distance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Element {
    private Distance distance;
    private Duration duration;
    private String status;
}
