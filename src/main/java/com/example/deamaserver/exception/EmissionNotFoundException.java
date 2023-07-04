package com.example.deamaserver.exception;

import com.example.deamaserver.error.ErrorCode;
import com.example.deamaserver.error.HackException;

public class EmissionNotFoundException extends HackException {
    public static final EmissionNotFoundException EXCEPTION = new EmissionNotFoundException();
    private EmissionNotFoundException() {
        super(ErrorCode.EMISSION_NOT_FOUND);
    }
}
