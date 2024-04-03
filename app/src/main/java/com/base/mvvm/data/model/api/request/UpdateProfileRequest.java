package com.base.mvvm.data.model.api.request;

import lombok.Data;

@Data
public class UpdateProfileRequest {
    private String name;
    private String email;
    private String password;
    private String newPassword;
}
