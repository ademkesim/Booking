package com.example.booking.Model.Business.Concrete;

import com.example.booking.Model.Business.Abstract.IUserService;
import com.example.booking.Model.DataAccess.Room.Contexts.AppDatabase;
import com.example.booking.Model.Entities.Concrete.User;

import java.util.List;

public class UserManager implements IUserService {
    private AppDatabase _appDatabase;

    public UserManager(AppDatabase appDatabase) {
        _appDatabase = appDatabase;
    }


    @Override
    public List<User> GetList() {
        return _appDatabase.userService().GetList();
    }

    @Override
    public User GetById(int id) {
        return _appDatabase.userService().GetById(id);
    }

    @Override
    public User GetByMail(String mail) { return _appDatabase.userService().GetByMail(mail);}

    @Override
    public void Add(User user) {
        _appDatabase.userService().Add(user);
    }

    @Override
    public void Update(User user) {
        _appDatabase.userService().Update(user);
    }

    @Override
    public void Delete(User user) {
        _appDatabase.userService().Delete(user);
    }
}
