package com.base.mvvm.data.model.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingCreateRequest {
    private String pickupAddress;
    private String destinationAddress;
    private Double pickupLat;
    private Double pickupLong;
    private Double destinationLat;
    private Double destinationLong;
    private Double distance;
    private Double money;
    private Double promotionMoney;
    private Long promotionId;
    private Long serviceId;
    private String customerNote;
    private Integer paymentKind;
}
