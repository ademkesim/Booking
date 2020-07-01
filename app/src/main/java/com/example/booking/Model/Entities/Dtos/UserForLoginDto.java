package com.example.booking.Model.Entities.Dtos;


import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.example.booking.Model.Entities.Abstract.IDto;

@Entity(tableName = "User")
public class UserForLoginDto implements IDto {
    @ColumnInfo(name= "mail")
    private String mail;
    @ColumnInfo(name= "password")
    private String password;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
