package com.example.booking.Model.Business.Utilities.Result;


public class ErrorDataResult<T> extends DataResult<T> {
    public ErrorDataResult(String message, T data) {
        super(false,message, data);
    }

    public ErrorDataResult(boolean success, T data) {
        super(success, data);
    }

    public ErrorDataResult(String message) {
        super( false,message,null);
    }
    public ErrorDataResult(){
        super(false,null);
    }
}
