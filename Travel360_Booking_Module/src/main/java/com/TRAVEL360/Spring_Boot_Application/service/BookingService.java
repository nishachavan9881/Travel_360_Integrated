package com.TRAVEL360.Spring_Boot_Application.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TRAVEL360.Spring_Boot_Application.entity.Booking;
import com.TRAVEL360.Spring_Boot_Application.entity.Reservation;
import com.TRAVEL360.Spring_Boot_Application.enums.BookingStatus;
import com.TRAVEL360.Spring_Boot_Application.enums.ReservationStatus;
import com.TRAVEL360.Spring_Boot_Application.exception.BadRequestException;
import com.TRAVEL360.Spring_Boot_Application.exception.ResourceNotFoundException;
import com.TRAVEL360.Spring_Boot_Application.repository.BookingRepository;
import com.TRAVEL360.Spring_Boot_Application.repository.ReservationRepository;

@Service
@Transactional
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ReservationRepository reservationRepository;

    public BookingService(BookingRepository bookingRepository,
                          ReservationRepository reservationRepository) {
        this.bookingRepository = bookingRepository;
        this.reservationRepository = reservationRepository;
    }

    public Booking createBooking(Booking booking, Reservation reservation) {

        if ("FLIGHT".equalsIgnoreCase(booking.getInventoryType())) {
            boolean blocked =
                    reservationRepository.existsByFlightNumberAndSeatNumberAndStatusIn(
                            reservation.getFlightNumber(),
                            reservation.getSeatNumber(),
                            List.of(ReservationStatus.PENDING, ReservationStatus.ACTIVE)
                    );
            if (blocked) throw new BadRequestException("Seat already booked");
        }

        if ("HOTEL".equalsIgnoreCase(booking.getInventoryType())) {
            boolean blocked =
                    reservationRepository
                            .existsByHotelNameAndRoomNumberAndStatusInAndStartDateLessThanAndEndDateGreaterThan(
                                    reservation.getHotelName(),
                                    reservation.getRoomNumber(),
                                    List.of(ReservationStatus.PENDING, ReservationStatus.ACTIVE),
                                    reservation.getEndDate(),
                                    reservation.getStartDate()
                            );
            if (blocked) throw new BadRequestException("Room already booked");
        }

        if ("CAB".equalsIgnoreCase(booking.getInventoryType())) {
            boolean blocked =
                    reservationRepository.existsByCabNumberAndStatusIn(
                            reservation.getCabNumber(),
                            List.of(ReservationStatus.PENDING, ReservationStatus.ACTIVE)
                    );
            if (blocked) throw new BadRequestException("Cab already booked");
        }

        booking.setStatus(BookingStatus.PENDING);
        booking.setCreatedAt(LocalDateTime.now());
        Booking savedBooking = bookingRepository.save(booking);

        reservation.setBookingId(savedBooking.getBookingId());
        reservation.setStatus(ReservationStatus.PENDING);
        reservationRepository.save(reservation);

        return savedBooking;
    }

    public Booking confirmBooking(Long bookingId) {

        Booking booking = getBookingById(bookingId);

        if (booking.getStatus() != BookingStatus.PENDING) {
            throw new BadRequestException("Only pending bookings can be confirmed");
        }

        booking.setStatus(BookingStatus.CONFIRMED);
        bookingRepository.save(booking);

        Reservation reservation = reservationRepository.findByBookingId(bookingId);
        reservation.setStatus(ReservationStatus.ACTIVE);
        reservationRepository.save(reservation);

        return booking;
    }

    // COMPLETE for FLIGHT / CAB / HOTEL
    public void completeBooking(Long bookingId) {

        Booking booking = getBookingById(bookingId);

        if (booking.getStatus() != BookingStatus.CONFIRMED) {
            throw new BadRequestException("Only confirmed bookings can be completed");
        }

        booking.setStatus(BookingStatus.COMPLETED);
        bookingRepository.save(booking);

        Reservation reservation = reservationRepository.findByBookingId(bookingId);
        reservation.setStatus(ReservationStatus.COMPLETED);
        reservationRepository.save(reservation);
    }

    public void cancelBooking(Long bookingId) {

        Booking booking = getBookingById(bookingId);

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new BadRequestException("Booking already cancelled");
        }

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);

        Reservation reservation = reservationRepository.findByBookingId(bookingId);
        reservation.setStatus(ReservationStatus.CANCELLED);
        reservationRepository.save(reservation);
    }

    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found with id " + bookingId));
    }
}
