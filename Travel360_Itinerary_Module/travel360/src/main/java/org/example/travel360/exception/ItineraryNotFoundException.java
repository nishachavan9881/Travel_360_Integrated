package org.example.travel360.exception;

public class ItineraryNotFoundException extends RuntimeException {
    
    public ItineraryNotFoundException(String message) {
        super(message);
    }
    
    public ItineraryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

