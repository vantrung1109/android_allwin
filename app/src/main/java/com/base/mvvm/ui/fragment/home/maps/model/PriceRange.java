package com.base.mvvm.ui.fragment.home.maps.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceRange {
    int from;
    int to;
    double price;
}
