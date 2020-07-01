package com.example.booking.Model.Business.Utilities.Result;

public class ErrorResult extends Result {
    public ErrorResult(boolean success, String message) {
        super(success, message);
    }

    public ErrorResult(boolean success) {
        super(success);
    }
}
