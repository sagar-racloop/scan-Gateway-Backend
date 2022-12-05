package com.abdm.sharegateway.exception;

public class NoStackException extends RuntimeException {

    public NoStackException(String message) {
        super(message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
