package com.fiap.ponto.controller;

import com.fiap.ponto.commons.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fiap.ponto.controller.dto.ErrorResponseDTO;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseBody
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleBindException(BindException bindException) {
        StringBuilder sb = new StringBuilder();
        bindException.getAllErrors().forEach(error -> sb.append(error.getDefaultMessage()).append("; "));
        return ErrorResponseDTO.builder().message(sb.toString()).build();
    }

}
