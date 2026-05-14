package org.example.travel360.exception;

public class InvalidBookingStatusException extends RuntimeException {
    
    public InvalidBookingStatusException(String message) {
        super(message);
    }
    
    public InvalidBookingStatusException(String message, Throwable cause) {
        super(message, cause);
    }
}

