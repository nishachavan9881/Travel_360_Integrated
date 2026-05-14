package com.example.travel360.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;



public class InvoiceRequestDto {


    @NotNull(message = "Booking ID is required")
    private Long bookingId;

    @NotNull(message = "Invoice amount is required")
    @Positive(message = "Invoice amount must be greater than zero")
    private BigDecimal invoiceAmount;




    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }
}