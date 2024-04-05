package com.base.mvvm.data.model.api.response.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetails {
    private Long id;
    private Integer codPrice;
    private String consigneeName;
    private String consigneePhone;
    private String customerNote;
    private String destinationAdress;
    private String destinationLat;
    private String destinationLong;
    private Integer distance;
    private Boolean isCod;
    private Double money;
    private String pickupAddress;
    private Double pickupLat;
    private Double pickupLong;
    private Double promotionMoney;
    private String senderName;
    private String senderPhone;

}
