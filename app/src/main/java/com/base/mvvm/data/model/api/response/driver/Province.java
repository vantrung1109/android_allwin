package com.base.mvvm.data.model.api.response.driver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Province {
    private Integer id;
    private Integer kind;
    private String name;
    private Parent parent;
    private String postCode;
}
