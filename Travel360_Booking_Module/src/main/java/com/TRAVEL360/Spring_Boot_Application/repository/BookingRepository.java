package com.TRAVEL360.Spring_Boot_Application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TRAVEL360.Spring_Boot_Application.entity.Booking;
import com.TRAVEL360.Spring_Boot_Application.enums.BookingStatus;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUserId(Long userId);

    List<Booking> findByStatus(BookingStatus status);
}
