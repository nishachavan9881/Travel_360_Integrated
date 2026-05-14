package org.example.travel360.repository;


import org.example.travel360.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    // Fetch reservation using bookingId
    Optional<Reservation> findByBookingId(Integer bookingId);
}