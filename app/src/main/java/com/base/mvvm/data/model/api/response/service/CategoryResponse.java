package com.base.mvvm.data.model.api.response.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private Integer id;
    private Integer kind;
    private String name;
    private Integer status;
}
