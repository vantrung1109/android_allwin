package com.base.mvvm.data.model.api.response.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String avatar;
    private Integer status;
    public void setData(AccountResponse accountResponse){
        this.id = accountResponse.getId();
        this.name = accountResponse.getName();
        this.phone = accountResponse.getPhone();
        this.email = accountResponse.getEmail();
        this.avatar = accountResponse.getAvatar();
        this.status = accountResponse.getStatus();
    }
}
