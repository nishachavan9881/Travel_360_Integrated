package com.example.travel360.controller;

import com.example.travel360.dto.InvoiceRequestDto;
import com.example.travel360.dto.InvoiceResponseDto;
import com.example.travel360.service.InvoiceService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Invoice REST Controller (DTO based)
 */
@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public InvoiceResponseDto createInvoice( @Valid @RequestBody InvoiceRequestDto dto) {
        return invoiceService.createInvoice(dto);
    }

    @GetMapping
    public List<InvoiceResponseDto> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }
}