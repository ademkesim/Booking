package com.example.booking.Model.Business.Utilities.Result;

public class SuccessDataResult<T> extends DataResult<T> {

    public SuccessDataResult(boolean success, String message, T data) {
        super(success, message, data);
    }

    public SuccessDataResult(boolean success, T data) {
        super(success, data);
    }
}
