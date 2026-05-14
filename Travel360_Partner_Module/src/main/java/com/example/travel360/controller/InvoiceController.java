package com.example.travel360.controller;

import com.example.travel360.dto.InvoiceRequestDto;
import com.example.travel360.dto.InvoiceResponseDto;
import com.example.travel360.dto.PaymentResponseDto;
import com.example.travel360.service.InvoiceService;
import com.example.travel360.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final PaymentService paymentService;

    public InvoiceController(
            InvoiceService invoiceService,
            PaymentService paymentService) {
        this.invoiceService = invoiceService;
        this.paymentService = paymentService;
    }

    @PostMapping
    public InvoiceResponseDto createInvoice(
            @Valid @RequestBody InvoiceRequestDto dto) {
        return invoiceService.createInvoice(dto);
    }

    @GetMapping
    public List<InvoiceResponseDto> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GetMapping("/{invoiceId}")
    public InvoiceResponseDto getInvoiceById(
            @PathVariable Long invoiceId) {
        return invoiceService.getInvoiceById(invoiceId);
    }

    //  payments for invoice
    @GetMapping("/{invoiceId}/payments")
    public List<PaymentResponseDto> getPaymentsByInvoice(
            @PathVariable Long invoiceId) {
        return paymentService.getPaymentsByInvoiceId(invoiceId);
    }
}