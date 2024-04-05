package com.base.mvvm.data.model.api.response.customer;

import com.base.mvvm.data.model.api.response.BaseResponse;

import java.util.Date;

import lombok.Data;

@Data
public class LoginResponse extends BaseResponse {
    private String username;
    private String token;
    private String fullName;
    private long id;
    private Date expired;
    private Integer kind;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
