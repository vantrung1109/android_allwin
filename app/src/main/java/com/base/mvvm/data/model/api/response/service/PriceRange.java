package com.base.mvvm.data.model.api.response.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceRange {
    Integer from;
    Integer to;
    Double price;
}
