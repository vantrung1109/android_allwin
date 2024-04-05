package com.base.mvvm.data.model.api.response.driver_vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
    private Long id;
    private Integer kind;
    private String name;
    private Integer status;
}
