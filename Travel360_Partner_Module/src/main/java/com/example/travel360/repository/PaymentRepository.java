package com.example.travel360.repository;

import com.example.travel360.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByInvoiceInvoiceId(Long invoiceId);
}