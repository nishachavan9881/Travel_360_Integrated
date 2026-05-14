package com.TRAVEL360.Spring_Boot_Application.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TRAVEL360.Spring_Boot_Application.entity.Reservation;
import com.TRAVEL360.Spring_Boot_Application.enums.ReservationStatus;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Reservation findByBookingId(Long bookingId);

    // FLIGHT
    boolean existsByFlightNumberAndSeatNumberAndStatusIn(
            String flightNumber,
            String seatNumber,
            List<ReservationStatus> statuses
    );

    // HOTEL
    boolean existsByHotelNameAndRoomNumberAndStatusInAndStartDateLessThanAndEndDateGreaterThan(
            String hotelName,
            String roomNumber,
            List<ReservationStatus> statuses,
            LocalDate endDate,
            LocalDate startDate
    );

    // CAB
    boolean existsByCabNumberAndStatusIn(
            String cabNumber,
            List<ReservationStatus> statuses
    );
}