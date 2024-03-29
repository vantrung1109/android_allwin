package com.base.mvvm.data.model.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SigninResponse {
    private String username;
    private long user_id;
    private Integer user_kind;
    private String access_token;
    private String token_type;
    private String refresh_token;

}
