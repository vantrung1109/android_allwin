package com.base.mvvm.data.model.api.api_search;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prediction {
    private String description;
    private List<MatchedSubString> matched_substrings;
    private String place_id;
    private String reference;
    private StructuredFormatting structured_formatting;
    private List<Terms> terms;
    private List<String> types;

}
