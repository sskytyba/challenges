package com.bus.routes.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class RouteControllerAdvice {

    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String requestParam(MissingServletRequestParameterException ex) {
        log.error("Error missing param. Message: {}", ex.getMessage());
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String requestParam(TypeMismatchException ex) {
        log.error("Error type mismatch. Message: {}", ex.getMessage());
        return ex.getMessage();
    }
}
