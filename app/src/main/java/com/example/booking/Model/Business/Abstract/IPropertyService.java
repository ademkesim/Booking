package com.example.booking.Model.Business.Abstract;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.booking.Model.Entities.Concrete.Property;

import java.util.List;

@Dao
public interface IPropertyService extends ICrudService<Property> {
    @Query("SELECT * FROM Property WHERE userId = :userId")
    List<Property> GetList(int userId);
    @Query("SELECT * FROM Property WHERE propertyId = :propertyId")
    Property GetById(int propertyId);
}
