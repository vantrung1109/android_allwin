package com.base.mvvm.data.model.api.map_search;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StructuredFormatting {
    String main_text;
    List<MatchedSubString> main_text_matched_substrings;
    String secondary_text;
    List<MatchedSubString> secondary_text_matched_substrings;
}
