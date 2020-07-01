package com.example.booking.Model.Business.Utilities.Result;

public interface IDataResult<T> extends IResult {
    T getData();
    boolean getSuccess();
    String getMessage();
}
