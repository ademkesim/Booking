package com.example.booking.Model.Business.Abstract;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.booking.Model.Entities.Concrete.User;

@Dao
public interface IAuthService {
    @Insert
    void Register(User user);
}
