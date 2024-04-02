package com.base.mvvm.data.model.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    private long id;
    private String name;
    private String phone;
    private String email;
    private String avatar;
    private int status;
    public void setData(AccountResponse accountResponse){
        this.id = accountResponse.getId();
        this.name = accountResponse.getName();
        this.phone = accountResponse.getPhone();
        this.email = accountResponse.getEmail();
        this.avatar = accountResponse.getAvatar();
        this.status = accountResponse.getStatus();
    }
}
