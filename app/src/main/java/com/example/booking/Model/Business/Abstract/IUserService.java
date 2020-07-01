package com.example.booking.Model.Business.Abstract;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.booking.Model.Entities.Concrete.User;

import java.util.List;


@Dao
public interface IUserService extends ICrudService<User>
{
    @Query("SELECT * FROM User")
    List<User> GetList();
    @Query("SELECT * FROM User Where UserId LIKE :id")
    User GetById(int id);
    @Query("SELECT * FROM User Where mail LIKE :mail")
    User GetByMail(String mail);
}
