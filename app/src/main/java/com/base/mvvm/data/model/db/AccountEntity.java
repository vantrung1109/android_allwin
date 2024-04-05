package com.base.mvvm.data.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.base.mvvm.data.model.api.response.customer.AccountResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = "db_accounts")
public class AccountEntity extends BaseEntity{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public Long id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "email")
    public String email;
    @ColumnInfo(name = "phone")
    public String phone;
    @ColumnInfo(name = "password")
    public String password;
    @ColumnInfo(name = "avatar")
    public String avatar;
    @ColumnInfo(name = "status")
    public String status;

    public AccountEntity() {
    }

    public AccountEntity(AccountResponse accountResponse) {
        this.id = accountResponse.getId();
        this.name = accountResponse.getName();
        this.email = accountResponse.getEmail();
        this.phone = accountResponse.getPhone();
        this.avatar = accountResponse.getAvatar();
    }
}
