package com.drishticon.kencoreapp.exception.handler;

import com.drishticon.kencoreapp.exception.ApiAuthenticationException;
import com.drishticon.kencoreapp.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiAuthenticationExceptionHandler {

    @ExceptionHandler(ApiAuthenticationException.class)
    public ResponseEntity throwApiException(ApiAuthenticationException e) {
        HttpStatus status = HttpStatus.NETWORK_AUTHENTICATION_REQUIRED;
        ErrorResponse error = new ErrorResponse(String.valueOf(status.value())
                ,e.getMessage() );
        return new ResponseEntity(error, status);
    }
}
