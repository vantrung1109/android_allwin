package com.base.mvvm.data.model.api.map_search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchedSubString {
    private Integer length;
    private Integer offset;
}
