package com.Projekti.Ikub.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
public class ExceptionMessage {


public static final String NOT_FOUND = "ENTITY %s with id %d not found";

    public ExceptionMessage(HttpStatus badRequest, Map<String, String> requiredFields) {
    }
}
