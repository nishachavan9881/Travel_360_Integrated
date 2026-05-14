package com.example.travel360.service.impl;

import com.example.travel360.dto.PaymentRequestDto;
import com.example.travel360.dto.PaymentResponseDto;
import com.example.travel360.entity.Invoice;
import com.example.travel360.entity.InvoiceStatus;
import com.example.travel360.entity.Payment;
import com.example.travel360.entity.PaymentStatus;
import com.example.travel360.exception.BadRequestException;
import com.example.travel360.exception.ResourceNotFoundException;
import com.example.travel360.repository.InvoiceRepository;
import com.example.travel360.repository.PaymentRepository;
import com.example.travel360.service.PaymentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;

    public PaymentServiceImpl(
            PaymentRepository paymentRepository,
            InvoiceRepository invoiceRepository) {
        this.paymentRepository = paymentRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public PaymentResponseDto createPayment(PaymentRequestDto dto) {

        Invoice invoice = invoiceRepository.findById(dto.getInvoiceId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invoice not found"));

        // ✅ Prevent double payment
        if (invoice.getStatus() == InvoiceStatus.PAID) {
            throw new BadRequestException("Invoice is already paid");
        }

        // ✅ Validate payment amount
        if (dto.getAmountPaid()
                .compareTo(invoice.getInvoiceAmount()) != 0) {
            throw new BadRequestException(
                    "Payment amount must match invoice amount");
        }

        Payment payment = new Payment();
        payment.setInvoice(invoice);
        payment.setAmountPaid(dto.getAmountPaid());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setPaymentDate(LocalDateTime.now());

        Payment savedPayment = paymentRepository.save(payment);

        invoice.setStatus(InvoiceStatus.PAID);
        invoiceRepository.save(invoice);

        return mapToResponseDto(savedPayment);
    }

    @Override
    public List<PaymentResponseDto> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    public List<PaymentResponseDto> getPaymentsByInvoiceId(Long invoiceId) {

        invoiceRepository.findById(invoiceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invoice not found"));

        return paymentRepository.findByInvoiceInvoiceId(invoiceId)
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    private PaymentResponseDto mapToResponseDto(Payment payment) {

        PaymentResponseDto dto = new PaymentResponseDto();
        dto.setPaymentId(payment.getPaymentId());
        dto.setAmountPaid(payment.getAmountPaid());
        dto.setPaymentMethod(payment.getPaymentMethod());
        dto.setStatus(payment.getStatus());
        dto.setPaymentDate(payment.getPaymentDate());

        return dto;
    }
}