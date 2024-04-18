package com.base.mvvm.data.model.api.request;

import androidx.annotation.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileRequest {
    private String avatar;
    private String name;
    private String newPassword;
    private String oldPassword;
}
