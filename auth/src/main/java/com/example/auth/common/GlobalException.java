package com.example.auth.common;

public class GlobalException extends Throwable {

    private String code;

    private String message;

    private Exception exception;

    public GlobalException(String code) {
        this.code = code;
    }

    public GlobalException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public GlobalException(String code, String message, Exception exception) {
        this.code = code;
        this.message = message;
        this.exception = exception;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

}