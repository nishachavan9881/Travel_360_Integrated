package org.example.travel360.dto;

/**
 * Minimal Booking DTO for Itinerary Generation
 * Only includes fields needed for itinerary creation/update
 * Benefits: Reduced data transfer, better performance, clearer API contract
 */
public class BookingMinimalDTO {

    private Integer bookingId;
    private Integer userId;
    private String status;

    // Constructors


    public BookingMinimalDTO(Integer bookingId, Integer userId, String status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.status = status;
    }

    // Getters and Setters
    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookingMinimalDTO{" +
                "bookingId=" + bookingId +
                ", userId=" + userId +
                ", status='" + status + '\'' +
                '}';
    }
}


