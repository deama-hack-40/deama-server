package com.example.deamaserver.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class HackException extends RuntimeException {
    private final ErrorCode errorCode;
    public HackException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
