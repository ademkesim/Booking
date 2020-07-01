package com.example.booking.Model.DataAccess.Room.Contexts;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.booking.Model.Business.Abstract.IAuthService;
import com.example.booking.Model.Business.Abstract.IPropertyService;
import com.example.booking.Model.Business.Abstract.IUserService;
import com.example.booking.Model.Entities.Concrete.Property;
import com.example.booking.Model.Entities.Concrete.User;

@Database(entities = {User.class, Property.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract IUserService userService();
    public abstract IAuthService authService();
    public abstract IPropertyService propertyService();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "Booking")
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }
}