package com.base.mvvm.data.model.api.response.rating;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    Long id;
    String message;
    Integer star;
    String createdDate;
}
