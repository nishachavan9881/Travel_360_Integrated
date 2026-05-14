package com.example.travel360.service.impl;

import com.example.travel360.dto.PaymentRequestDto;
import com.example.travel360.dto.PaymentResponseDto;
import com.example.travel360.entity.Invoice;
import com.example.travel360.entity.InvoiceStatus;
import com.example.travel360.entity.Payment;
import com.example.travel360.entity.PaymentMethod;
import com.example.travel360.entity.PaymentStatus;
import com.example.travel360.exception.ResourceNotFoundException;
import com.example.travel360.repository.InvoiceRepository;
import com.example.travel360.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    void createPayment_shouldPersistPaymentMarkInvoicePaidAndReturnResponse() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(20L);
        invoice.setInvoiceAmount(new BigDecimal("1800.00"));
        invoice.setStatus(InvoiceStatus.GENERATED);

        PaymentRequestDto request = new PaymentRequestDto();
        request.setInvoiceId(20L);
        request.setAmountPaid(new BigDecimal("1800.00"));
        request.setPaymentMethod(PaymentMethod.UPI);

        Payment savedPayment = new Payment();
        savedPayment.setPaymentId(100L);
        savedPayment.setInvoice(invoice);
        savedPayment.setAmountPaid(new BigDecimal("1800.00"));
        savedPayment.setPaymentMethod(PaymentMethod.UPI);
        savedPayment.setStatus(PaymentStatus.SUCCESS);
        savedPayment.setPaymentDate(LocalDateTime.now());

        when(invoiceRepository.findById(20L)).thenReturn(Optional.of(invoice));
        when(paymentRepository.save(org.mockito.ArgumentMatchers.any(Payment.class))).thenReturn(savedPayment);

        PaymentResponseDto response = paymentService.createPayment(request);

        ArgumentCaptor<Payment> paymentCaptor = ArgumentCaptor.forClass(Payment.class);
        verify(paymentRepository).save(paymentCaptor.capture());
        Payment paymentToSave = paymentCaptor.getValue();

        assertEquals(invoice, paymentToSave.getInvoice());
        assertEquals(new BigDecimal("1800.00"), paymentToSave.getAmountPaid());
        assertEquals(PaymentMethod.UPI, paymentToSave.getPaymentMethod());
        assertEquals(PaymentStatus.SUCCESS, paymentToSave.getStatus());
        assertNotNull(paymentToSave.getPaymentDate());

        verify(invoiceRepository).save(invoice);
        assertEquals(InvoiceStatus.PAID, invoice.getStatus());

        assertEquals(100L, response.getPaymentId());
        assertEquals(new BigDecimal("1800.00"), response.getAmountPaid());
        assertEquals(PaymentMethod.UPI, response.getPaymentMethod());
        assertEquals(PaymentStatus.SUCCESS, response.getStatus());
    }

    @Test
    void getPaymentsByInvoiceId_shouldReturnPaymentsWhenInvoiceExists() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(44L);

        Payment payment = new Payment();
        payment.setPaymentId(7L);
        payment.setInvoice(invoice);
        payment.setAmountPaid(new BigDecimal("500.00"));
        payment.setPaymentMethod(PaymentMethod.CARD);
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setPaymentDate(LocalDateTime.now());

        when(invoiceRepository.findById(44L)).thenReturn(Optional.of(invoice));
        when(paymentRepository.findByInvoiceInvoiceId(44L)).thenReturn(List.of(payment));

        List<PaymentResponseDto> response = paymentService.getPaymentsByInvoiceId(44L);

        assertEquals(1, response.size());
        assertEquals(7L, response.get(0).getPaymentId());
        assertEquals(new BigDecimal("500.00"), response.get(0).getAmountPaid());
        assertEquals(PaymentMethod.CARD, response.get(0).getPaymentMethod());
    }

    @Test
    void getPaymentsByInvoiceId_shouldThrowWhenInvoiceNotFound() {
        when(invoiceRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> paymentService.getPaymentsByInvoiceId(999L));
    }
}

