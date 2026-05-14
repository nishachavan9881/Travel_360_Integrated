package org.example.travel360.exception;

import org.example.travel360.dto.SimpleErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());
    
    /**
     * Handle BookingNotFoundException
     */
    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<?> handleBookingNotFoundException(BookingNotFoundException ex, WebRequest request) {
        logger.severe("Booking not found: " + ex.getMessage());
        
        SimpleErrorResponseDTO errorResponse = new SimpleErrorResponseDTO(
                "BOOKING_NOT_FOUND",
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    
    /**
     * Handle ReservationNotFoundException
     */
    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<?> handleReservationNotFoundException(ReservationNotFoundException ex, WebRequest request) {
        logger.severe("Reservation not found: " + ex.getMessage());
        
        SimpleErrorResponseDTO errorResponse = new SimpleErrorResponseDTO(
                "RESERVATION_NOT_FOUND",
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    
    /**
     * Handle ItineraryNotFoundException
     */
    @ExceptionHandler(ItineraryNotFoundException.class)
    public ResponseEntity<?> handleItineraryNotFoundException(ItineraryNotFoundException ex, WebRequest request) {
        logger.severe("Itinerary not found: " + ex.getMessage());
        
        SimpleErrorResponseDTO errorResponse = new SimpleErrorResponseDTO(
                "ITINERARY_NOT_FOUND",
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    
    /**
     * Handle InvalidBookingStatusException
     */
    @ExceptionHandler(InvalidBookingStatusException.class)
    public ResponseEntity<?> handleInvalidBookingStatusException(InvalidBookingStatusException ex, WebRequest request) {
        logger.severe("Invalid booking status: " + ex.getMessage());
        
        SimpleErrorResponseDTO errorResponse = new SimpleErrorResponseDTO(
                "BOOKING_NOT_CONFIRMED",
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
    /**
     * Handle InvalidUserIdException
     */
    @ExceptionHandler(InvalidUserIdException.class)
    public ResponseEntity<?> handleInvalidUserIdException(InvalidUserIdException ex, WebRequest request) {
        logger.severe("Invalid user ID: " + ex.getMessage());
        
        SimpleErrorResponseDTO errorResponse = new SimpleErrorResponseDTO(
                "INVALID_USER_ID",
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
    /**
     * Handle generic exceptions
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        logger.severe("An unexpected error occurred: " + ex.getMessage());
        
        SimpleErrorResponseDTO errorResponse = new SimpleErrorResponseDTO(
                "INTERNAL_SERVER_ERROR",
                "An unexpected error occurred: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


