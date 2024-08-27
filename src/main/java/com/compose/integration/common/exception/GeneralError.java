package com.compose.integration.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Invalid Auth Token")
public class GeneralError extends RuntimeException {

    public GeneralError() {
    }

    public GeneralError(final String message) {
        super(message);
    }

}
