package com.example.booking.Model.Business.Concrete;

import com.example.booking.Model.Business.Abstract.IAuthService;
import com.example.booking.Model.Business.Hashing.HashingHelper;
import com.example.booking.Model.Business.Utilities.Messages.Messages;
import com.example.booking.Model.Business.Utilities.Result.ErrorDataResult;
import com.example.booking.Model.Business.Utilities.Result.IDataResult;
import com.example.booking.Model.Business.Utilities.Result.SuccessDataResult;
import com.example.booking.Model.DataAccess.Room.Contexts.AppDatabase;
import com.example.booking.Model.Entities.Concrete.User;
import com.example.booking.Model.Entities.Dtos.UserForLoginDto;

public class AuthManager implements IAuthService {
    private AppDatabase _appDatabase;

    public AuthManager(AppDatabase appDatabase) {
        _appDatabase = appDatabase;
    }
    @Override
    public void Register(User user) {
        String passwordHash = HashingHelper.hashPassword(user.getPassword());
        user.setPasswordHash(passwordHash);
        _appDatabase.authService().Register(user);
    }

    public IDataResult<User> Login(UserForLoginDto userForLoginDto) {
        UserManager userManager = new UserManager(_appDatabase);
        User userToCheck = userManager.GetByMail(userForLoginDto.getMail());
        if (userToCheck == null) {
            return new ErrorDataResult<User>(Messages.userNotFound);
        }
        if(!HashingHelper.checkPassword(userForLoginDto.getPassword(),userToCheck.getPasswordHash())){
            return new ErrorDataResult<User>(Messages.passwordError);
        }
        return new SuccessDataResult<User>(true,Messages.successLogin,userToCheck);
    }
}
