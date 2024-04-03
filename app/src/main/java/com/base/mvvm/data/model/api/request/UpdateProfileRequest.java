package com.base.mvvm.data.model.api.request;

import lombok.Data;

@Data
public class UpdateProfileRequest {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String avatar;
    private int status;
    private String password;
    private String newPassword;
}
