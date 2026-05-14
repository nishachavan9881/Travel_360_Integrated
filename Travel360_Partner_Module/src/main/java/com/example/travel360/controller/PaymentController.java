package com.example.travel360.controller;

import com.example.travel360.dto.PaymentRequestDto;
import com.example.travel360.dto.PaymentResponseDto;
import com.example.travel360.service.PaymentService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public PaymentResponseDto createPayment( @Valid @RequestBody PaymentRequestDto dto) {
        return paymentService.createPayment(dto);
    }

    @GetMapping
    public List<PaymentResponseDto> getAllPayments() {
        return paymentService.getAllPayments();
    }
}
