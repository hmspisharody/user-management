package com.drishticon.kencoreapp.exception.handler;

import com.drishticon.kencoreapp.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleException(MethodArgumentNotValidException e) {

        final HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> fields = e.getBindingResult().getFieldErrors().stream().map(field -> field.getField())
                .collect(Collectors.toList());
        ErrorResponse error = new ErrorResponse(String.valueOf(status.value()), "Missing Fields : " + String.join(",", fields));

        return new ResponseEntity(error, status);

    }
}
