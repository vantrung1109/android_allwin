package com.base.mvvm.data.model.api.response.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private Long id;
    private Integer kind;
    private String name;
    private Integer status;
}
