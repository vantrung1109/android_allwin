package com.base.mvvm.data.model.api.request;

import androidx.annotation.NonNull;

import lombok.Data;

@Data
public class UpdateProfileRequest {
    private String username;
    private String fullName;
    private String avatar;
    private String email;
    private String userOfficeName;
    private String phone;
    private String password;
    private String newPassword;
}
