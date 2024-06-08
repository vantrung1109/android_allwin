package com.base.mvvm.data.model.api.response.map.distance;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Row {
    private List<Element> elements;
}
