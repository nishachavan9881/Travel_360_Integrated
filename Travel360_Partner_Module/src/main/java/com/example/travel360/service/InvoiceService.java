package com.example.travel360.service;

import com.example.travel360.dto.InvoiceRequestDto;
import com.example.travel360.dto.InvoiceResponseDto;

import java.util.List;

public interface InvoiceService {

    InvoiceResponseDto createInvoice(InvoiceRequestDto dto);

    List<InvoiceResponseDto> getAllInvoices();

    InvoiceResponseDto getInvoiceById(Long invoiceId);
}