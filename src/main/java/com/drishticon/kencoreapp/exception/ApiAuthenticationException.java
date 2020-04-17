package com.drishticon.kencoreapp.exception;

public class ApiAuthenticationException extends RuntimeException {

    private String message;

    public ApiAuthenticationException(String message) {
        super(message);
    }
}
