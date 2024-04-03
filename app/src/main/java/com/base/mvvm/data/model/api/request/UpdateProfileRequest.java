package com.base.mvvm.data.model.api.request;

import androidx.annotation.NonNull;

import lombok.Data;

@Data
public class UpdateProfileRequest {
    private String name;
    private String email;

    private String newPassword;
    private String oldPassword;


    @Override
    public String toString() {
        return "UpdateProfileRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                '}';
    }
}
