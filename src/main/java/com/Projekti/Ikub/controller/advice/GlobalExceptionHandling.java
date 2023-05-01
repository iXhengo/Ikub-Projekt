package com.Projekti.Ikub.controller.advice;


import com.Projekti.Ikub.exceptions.BadRequestException;
import com.Projekti.Ikub.exceptions.ErrorFormat;
import com.Projekti.Ikub.exceptions.ExceptionMessage;
import com.Projekti.Ikub.exceptions.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandling extends ResponseEntityExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<ErrorFormat> handleError(RuntimeException e, HttpServletRequest httpServletRequest) {

        ErrorFormat errorFormat = new ErrorFormat(e.getMessage(), LocalDateTime.now());
        if (e instanceof BadRequestException) {
            return new ResponseEntity<>(errorFormat, HttpStatus.BAD_REQUEST);
        }

        if (e instanceof NotFoundException) {
            return new ResponseEntity<>(errorFormat, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(errorFormat, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionMessage msg = new ExceptionMessage(HttpStatus.BAD_REQUEST,getRequiredFields(ex));
        return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
    }

    private Map<String,String> getRequiredFields(MethodArgumentNotValidException ex){

        Map<String,String> errors= new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e ->{
            errors.put(e.getField(),e.getDefaultMessage());
        });

        return errors;
    }
    }

