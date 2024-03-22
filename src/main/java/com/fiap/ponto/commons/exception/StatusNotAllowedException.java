package com.fiap.ponto.commons.exception;

public class StatusNotAllowedException extends RuntimeException {
    public StatusNotAllowedException(String message) {
        super(message);
    }
}
