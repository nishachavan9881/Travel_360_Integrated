package com.TRAVEL360.Spring_Boot_Application.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.TRAVEL360.Spring_Boot_Application.dto.BookingRequestDTO;
import com.TRAVEL360.Spring_Boot_Application.entity.Booking;
import com.TRAVEL360.Spring_Boot_Application.entity.Reservation;
import com.TRAVEL360.Spring_Boot_Application.service.BookingService;
import com.TRAVEL360.Spring_Boot_Application.repository.BookingRepository;
import com.TRAVEL360.Spring_Boot_Application.repository.ReservationRepository;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final BookingRepository bookingRepository;
    private final ReservationRepository reservationRepository;

    public BookingController(
            BookingService bookingService,
            BookingRepository bookingRepository,
            ReservationRepository reservationRepository) {
        this.bookingService = bookingService;
        this.bookingRepository = bookingRepository;
        this.reservationRepository = reservationRepository;
    }

    //CREATE BOOKING
    @PostMapping
    public Booking createBooking(@RequestBody BookingRequestDTO request) {

        Booking booking = new Booking();
        booking.setUserId(request.getUserId());
        booking.setPartnerId(request.getPartnerId());
        booking.setInventoryType(request.getInventoryType());
        booking.setInventoryRefId(request.getInventoryRefId());
        booking.setBookingDate(request.getBookingDate());
        booking.setTotalAmount(request.getTotalAmount());

        Reservation reservation = new Reservation();
        reservation.setStartDate(request.getStartDate());
        reservation.setEndDate(request.getEndDate());
        reservation.setTravelOrStay(request.getTravelOrStay());

        reservation.setFlightNumber(request.getFlightNumber());
        reservation.setSeatNumber(request.getSeatNumber());

        reservation.setHotelName(request.getHotelName());
        reservation.setRoomNumber(request.getRoomNumber());

        reservation.setCabType(request.getCabType());
        reservation.setCabNumber(request.getCabNumber());

        return bookingService.createBooking(booking, reservation);
    }

    // GET BOOKING BY ID
    @GetMapping("/{bookingId}")
    public Booking getBookingById(@PathVariable Long bookingId) {
        return bookingService.getBookingById(bookingId);
    }

    // GET BOOKINGS BY USER
    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsByUser(@PathVariable Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    // GET RESERVATION BY BOOKING ID
    @GetMapping("/{bookingId}/reservation")
    public Reservation getReservation(@PathVariable Long bookingId) {
        return reservationRepository.findByBookingId(bookingId);
    }

    // CONFIRM BOOKING
    @PutMapping("/{bookingId}/confirm")
    public void confirmBooking(@PathVariable Long bookingId) {
        bookingService.confirmBooking(bookingId);
    }

    //  COMPLETE BOOKING
    @PutMapping("/{bookingId}/complete")
    public void completeBooking(@PathVariable Long bookingId) {
        bookingService.completeBooking(bookingId);
    }

    // CANCEL BOOKING
    @PutMapping("/{bookingId}/cancel")
    public void cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
    }
}