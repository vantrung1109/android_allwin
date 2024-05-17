package com.base.mvvm.data.model.api.address_by_placeid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Viewport {
    private Northeast northeast;
    private Southwest southwest;
}
