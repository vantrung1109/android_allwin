package com.base.mvvm.data.model.api.response.customer;

import java.io.Serializable;

import lombok.Data;

@Data
public class CustomerProfileResponse implements Serializable {
    private String avatar;
    private String email;
    private Long id;
    private String name;
    private String phone;
    private Integer status;

}
