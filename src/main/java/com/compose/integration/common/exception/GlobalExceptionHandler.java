package com.compose.integration.common.exception;

import com.compose.integration.common.dto.GenericException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @Autowired
    private MessageSource messages;

    @ExceptionHandler(value
            = {GeneralError.class})
    public ResponseEntity<Object> handleInvalidTokenException(GeneralError ex) {
        return new ResponseEntity<>(
                GenericException.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .errorCode("GENERAL_ERROR")
                        .message(messages.getMessage("error.general.error", null, LocaleContextHolder.getLocale()))
                        .build(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}

