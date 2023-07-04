package com.example.deamaserver.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    EMISSION_NOT_FOUND("the id is not in db", 404);

    private final String message;
    private final Integer status;
}
