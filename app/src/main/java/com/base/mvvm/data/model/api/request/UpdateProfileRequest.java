package com.base.mvvm.data.model.api.request;

import androidx.annotation.NonNull;

import lombok.Data;

@Data
public class UpdateProfileRequest {
    private String name;
    private String email;
    private String newPassword;
    private String oldPassword;
    private String imgAvar;

    @Override
    public String toString() {
        return "UpdateProfileRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", imgAvar='" + imgAvar + '\'' +
                '}';
    }
}
