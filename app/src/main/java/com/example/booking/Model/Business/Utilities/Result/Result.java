package com.example.booking.Model.Business.Utilities.Result;

public class Result implements IResult{
    public Result(boolean success, String message)
    {
        this.message = message;
    }
    public Result(boolean success)
    {
        this.success = success;
    }
    boolean success;
    String message;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

}
