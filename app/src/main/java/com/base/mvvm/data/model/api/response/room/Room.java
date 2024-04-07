package com.base.mvvm.data.model.api.response.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    Long id;
    String timeStart;
    String timeEnd;
}

