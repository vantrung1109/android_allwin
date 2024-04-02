package com.base.mvvm.data.model.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest extends  BaseRequest{
    private String name;
    private String email;
    private String phone;
    private String password;

}
