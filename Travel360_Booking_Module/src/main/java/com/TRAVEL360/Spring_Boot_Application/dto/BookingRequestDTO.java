package com.TRAVEL360.Spring_Boot_Application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class BookingRequestDTO {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Partner ID is required")
    private Long partnerId;

    @NotBlank(message = "Inventory type is required")
    private String inventoryType;

    @NotNull(message = "Inventory reference ID is required")
    private Long inventoryRefId;

    @NotNull(message = "Booking date is required")
    private LocalDate bookingDate;

    @NotNull(message = "Total amount is required")
    @Positive(message = "Total amount must be greater than zero")
    private BigDecimal totalAmount;

    // Reservation common fields
    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @NotBlank(message = "Travel or Stay type is required")
    private String travelOrStay;

    // Flight
    private String flightNumber;
    private String seatNumber;

    // Hotel
    private String hotelName;
    private String roomNumber;

    // Cab
    private String cabType;
    private String cabNumber;

    // Getters and Setters (unchanged)

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getPartnerId() { return partnerId; }
    public void setPartnerId(Long partnerId) { this.partnerId = partnerId; }

    public String getInventoryType() { return inventoryType; }
    public void setInventoryType(String inventoryType) { this.inventoryType = inventoryType; }

    public Long getInventoryRefId() { return inventoryRefId; }
    public void setInventoryRefId(Long inventoryRefId) { this.inventoryRefId = inventoryRefId; }

    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getTravelOrStay() { return travelOrStay; }
    public void setTravelOrStay(String travelOrStay) { this.travelOrStay = travelOrStay; }

    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    public String getHotelName() { return hotelName; }
    public void setHotelName(String hotelName) { this.hotelName = hotelName; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public String getCabType() { return cabType; }
    public void setCabType(String cabType) { this.cabType = cabType; }

    public String getCabNumber() { return cabNumber; }
    public void setCabNumber(String cabNumber) { this.cabNumber = cabNumber; }
}