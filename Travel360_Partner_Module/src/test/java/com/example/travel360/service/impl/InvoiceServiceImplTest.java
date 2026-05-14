package com.example.travel360.service.impl;

import com.example.travel360.dto.InvoiceRequestDto;
import com.example.travel360.dto.InvoiceResponseDto;
import com.example.travel360.entity.Invoice;
import com.example.travel360.entity.InvoiceStatus;
import com.example.travel360.exception.ResourceNotFoundException;
import com.example.travel360.repository.InvoiceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceImplTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @Test
    void createInvoice_shouldPersistInvoiceAndReturnResponse() {
        InvoiceRequestDto request = new InvoiceRequestDto();
        request.setBookingId(101L);
        request.setInvoiceAmount(new BigDecimal("2500.00"));

        Invoice savedInvoice = new Invoice();
        savedInvoice.setInvoiceId(1L);
        savedInvoice.setBookingId(101L);
        savedInvoice.setInvoiceAmount(new BigDecimal("2500.00"));
        savedInvoice.setStatus(InvoiceStatus.GENERATED);
        savedInvoice.setInvoiceDate(LocalDateTime.now());

        when(invoiceRepository.save(org.mockito.ArgumentMatchers.any(Invoice.class))).thenReturn(savedInvoice);

        InvoiceResponseDto response = invoiceService.createInvoice(request);

        ArgumentCaptor<Invoice> captor = ArgumentCaptor.forClass(Invoice.class);
        verify(invoiceRepository).save(captor.capture());
        Invoice invoiceToSave = captor.getValue();

        assertEquals(101L, invoiceToSave.getBookingId());
        assertEquals(new BigDecimal("2500.00"), invoiceToSave.getInvoiceAmount());
        assertEquals(InvoiceStatus.GENERATED, invoiceToSave.getStatus());
        assertNotNull(invoiceToSave.getInvoiceDate());

        assertEquals(1L, response.getInvoiceId());
        assertEquals(new BigDecimal("2500.00"), response.getInvoiceAmount());
        assertEquals(InvoiceStatus.GENERATED, response.getStatus());
        assertNotNull(response.getInvoiceDate());
    }

    @Test
    void getInvoiceById_shouldReturnInvoiceWhenFound() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(9L);
        invoice.setInvoiceAmount(new BigDecimal("999.99"));
        invoice.setStatus(InvoiceStatus.PAID);
        invoice.setInvoiceDate(LocalDateTime.now());

        when(invoiceRepository.findById(9L)).thenReturn(Optional.of(invoice));

        InvoiceResponseDto response = invoiceService.getInvoiceById(9L);

        assertEquals(9L, response.getInvoiceId());
        assertEquals(new BigDecimal("999.99"), response.getInvoiceAmount());
        assertEquals(InvoiceStatus.PAID, response.getStatus());
    }

    @Test
    void getInvoiceById_shouldThrowWhenNotFound() {
        when(invoiceRepository.findById(55L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> invoiceService.getInvoiceById(55L));
    }
}

