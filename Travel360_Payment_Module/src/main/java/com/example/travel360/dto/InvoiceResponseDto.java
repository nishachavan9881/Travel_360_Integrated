package com.example.travel360.dto;

import com.example.travel360.entity.InvoiceStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO used to SEND invoice data to client
 */
public class InvoiceResponseDto {

    private Long invoiceId;
    private BigDecimal invoiceAmount;
    private InvoiceStatus status;
    private LocalDateTime invoiceDate;

    // ===== Getters & Setters =====

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    public LocalDateTime getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDateTime invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
}