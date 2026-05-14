package com.TRAVEL360.Spring_Boot_Application.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
