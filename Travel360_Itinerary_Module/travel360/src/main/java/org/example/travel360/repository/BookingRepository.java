package org.example.travel360.repository;

import org.example.travel360.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    // Fetch booking using userId
    Optional<Booking> findByUserId(Integer userId);
}