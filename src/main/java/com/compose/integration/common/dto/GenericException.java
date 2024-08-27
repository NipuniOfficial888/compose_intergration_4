package com.compose.integration.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GenericException {
    private int status;
    private String error;
    private String errorCode;
    private String message;
}
