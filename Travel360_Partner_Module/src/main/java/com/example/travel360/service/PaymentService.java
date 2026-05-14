package com.example.travel360.service;

import com.example.travel360.dto.PaymentRequestDto;
import com.example.travel360.dto.PaymentResponseDto;

import java.util.List;

public interface PaymentService {

    PaymentResponseDto createPayment(PaymentRequestDto dto);

    List<PaymentResponseDto> getAllPayments();

    List<PaymentResponseDto> getPaymentsByInvoiceId(Long invoiceId);
}