package com.base.mvvm.data.model.api.api_search;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchPlaceApi {
    private List<Prediction> predictions;
    private String status;
}
