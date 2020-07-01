package com.example.booking.Model.Business.Utilities.Result;

public class SuccessResult extends Result {
    public SuccessResult(boolean success, String message) {
        super(success, message);
    }

    public SuccessResult(boolean success) {
        super(success);
    }
}
