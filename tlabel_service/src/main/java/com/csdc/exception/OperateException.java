package com.csdc.exception;

public class OperateException extends RuntimeException {
    public OperateException() {
        super();
    }

    public OperateException(OperateError error) {
        super(error.toString());
    }
}
