package com.example.travel360.service.impl;

import com.example.travel360.dto.PaymentRequestDto;
import com.example.travel360.dto.PaymentResponseDto;
import com.example.travel360.entity.Invoice;
import com.example.travel360.entity.InvoiceStatus;
import com.example.travel360.entity.Payment;
import com.example.travel360.entity.PaymentStatus;
import com.example.travel360.exception.ResourceNotFoundException;
import com.example.travel360.repository.InvoiceRepository;
import com.example.travel360.repository.PaymentRepository;
import com.example.travel360.service.PaymentService;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Payment service implementation
 */
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

        // 1. Fetch Invoice
        Invoice invoice = invoiceRepository.findById(dto.getInvoiceId())
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found"));

        // 2. DTO -> Entity
        Payment payment = new Payment();
        payment.setInvoice(invoice);
        payment.setAmountPaid(dto.getAmountPaid());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setStatus(PaymentStatus.SUCCESS);

        // 3. Save Payment
        Payment savedPayment = paymentRepository.save(payment);

        // 4. Update Invoice status
        invoice.setStatus(InvoiceStatus.PAID);
        invoiceRepository.save(invoice);

        // 5. Entity -> DTO
        return mapToResponseDto(savedPayment);
    }

    @Override
    public List<PaymentResponseDto> getAllPayments() {

        return paymentRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    // ===== Mapping Helper =====
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
