package com.example.travel360.service.impl;

import com.example.travel360.dto.InvoiceRequestDto;
import com.example.travel360.dto.InvoiceResponseDto;
import com.example.travel360.entity.Invoice;
import com.example.travel360.entity.InvoiceStatus;
import com.example.travel360.exception.ResourceNotFoundException;
import com.example.travel360.repository.InvoiceRepository;
import com.example.travel360.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public InvoiceResponseDto createInvoice(InvoiceRequestDto dto) {

        Invoice invoice = new Invoice();
        invoice.setBookingId(dto.getBookingId());
        invoice.setInvoiceAmount(dto.getInvoiceAmount());
        invoice.setStatus(InvoiceStatus.GENERATED);
        invoice.setInvoiceDate(LocalDateTime.now());

        return mapToResponseDto(invoiceRepository.save(invoice));
    }

    @Override
    public List<InvoiceResponseDto> getAllInvoices() {
        return invoiceRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    public InvoiceResponseDto getInvoiceById(Long invoiceId) {

        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invoice not found"));

        return mapToResponseDto(invoice);
    }

    private InvoiceResponseDto mapToResponseDto(Invoice invoice) {

        InvoiceResponseDto dto = new InvoiceResponseDto();
        dto.setInvoiceId(invoice.getInvoiceId());
        dto.setInvoiceAmount(invoice.getInvoiceAmount());
        dto.setStatus(invoice.getStatus());
        dto.setInvoiceDate(invoice.getInvoiceDate());

        return dto;
    }
}