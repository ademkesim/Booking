package com.example.booking.Model.Business.Abstract;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface ICrudService<TEntity> {
    @Insert
    void Add(TEntity entity);
    @Update
    void Update(TEntity entity);
    @Delete
    void Delete(TEntity entity);
}
