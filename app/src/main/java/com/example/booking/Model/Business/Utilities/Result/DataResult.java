package com.example.booking.Model.Business.Utilities.Result;

public class DataResult<T> extends Result implements IDataResult<T> {

    public T data;
    public boolean success;
    public String message;

    public DataResult(boolean success, String message, T data) {
        super(success, message);
        this.data = data;
        this.success = success;
        this.message = message;
    }

    public DataResult(boolean success, T data) {
        super(success);
        this.data = data;
        this.success = success;
    }

    public T getData() {
        return this.data;
    }
    public boolean getSuccess() {
        return this.success;
    }
    public String getString() {
        return this.message;
    }
}
