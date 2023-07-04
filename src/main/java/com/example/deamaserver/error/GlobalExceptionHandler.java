package com.example.deamaserver.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({HackException.class})
    public ResponseEntity<ErrorResponse> hackExceptionHandler(HackException e) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .status(e.getErrorCode().getStatus())
                .message(e.getErrorCode().getMessage())
                .build(), HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }
}
