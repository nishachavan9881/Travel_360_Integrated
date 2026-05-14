package com.TRAVEL360.Spring_Boot_Application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.TRAVEL360.Spring_Boot_Application.entity.Booking;
import com.TRAVEL360.Spring_Boot_Application.entity.Reservation;
import com.TRAVEL360.Spring_Boot_Application.enums.BookingStatus;
import com.TRAVEL360.Spring_Boot_Application.enums.ReservationStatus;
import com.TRAVEL360.Spring_Boot_Application.exception.BadRequestException;
import com.TRAVEL360.Spring_Boot_Application.exception.ResourceNotFoundException;
import com.TRAVEL360.Spring_Boot_Application.repository.BookingRepository;
import com.TRAVEL360.Spring_Boot_Application.repository.ReservationRepository;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private BookingService bookingService;

    private Booking booking;
    private Reservation reservation;

    @BeforeEach
    void setup() {
        booking = new Booking();
        booking.setUserId(1L);
        booking.setPartnerId(1L);
        booking.setInventoryType("HOTEL");
        booking.setInventoryRefId(1L);
        booking.setBookingDate(LocalDate.now());
        booking.setTotalAmount(BigDecimal.valueOf(9000));

        reservation = new Reservation();
        reservation.setHotelName("Grand Palace");
        reservation.setRoomNumber("DELUXE-101");
        reservation.setStartDate(LocalDate.now().plusDays(1));
        reservation.setEndDate(LocalDate.now().plusDays(3));
    }

    // ✅ TEST 1 — Create booking success (HOTEL)
    @Test
    void createBooking_success() {
        when(
                reservationRepository
                        .existsByHotelNameAndRoomNumberAndStatusInAndStartDateLessThanAndEndDateGreaterThan(
                                anyString(),
                                anyString(),
                                anyList(),
                                any(LocalDate.class),
                                any(LocalDate.class)
                        )
        ).thenReturn(false);

        when(bookingRepository.save(any(Booking.class))).thenAnswer(invocation -> {
            Booking saved = invocation.getArgument(0);
            saved.setBookingId(1L);
            saved.setStatus(BookingStatus.PENDING);
            return saved;
        });

        Booking result = bookingService.createBooking(booking, reservation);

        assertNotNull(result);
        assertEquals(BookingStatus.PENDING, result.getStatus());

        verify(bookingRepository).save(any(Booking.class));
        verify(reservationRepository).save(any(Reservation.class));
    }

    // ❌ TEST 2 — Duplicate FLIGHT seat
    @Test
    void createBooking_duplicateFlightSeat_shouldFail() {
        booking.setInventoryType("FLIGHT");
        reservation.setFlightNumber("AI203");
        reservation.setSeatNumber("10A");

        when(
                reservationRepository.existsByFlightNumberAndSeatNumberAndStatusIn(
                        eq("AI203"),
                        eq("10A"),
                        anyList()
                )
        ).thenReturn(true);

        BadRequestException ex = assertThrows(
                BadRequestException.class,
                () -> bookingService.createBooking(booking, reservation)
        );

        assertTrue(ex.getMessage().contains("Seat"));
    }

    // ❌ TEST 3 — Duplicate CAB booking
    @Test
    void createBooking_duplicateCab_shouldFail() {
        booking.setInventoryType("CAB");
        reservation.setCabNumber("TN09AB1234");

        when(
                reservationRepository.existsByCabNumberAndStatusIn(
                        eq("TN09AB1234"),
                        anyList()
                )
        ).thenReturn(true);

        assertThrows(
                BadRequestException.class,
                () -> bookingService.createBooking(booking, reservation)
        );
    }

    // ✅ TEST 4 — Confirm booking
    @Test
    void confirmBooking_success() {
        booking.setBookingId(1L);
        booking.setStatus(BookingStatus.PENDING);

        reservation.setStatus(ReservationStatus.PENDING);

        when(bookingRepository.findById(1L))
                .thenReturn(java.util.Optional.of(booking));

        when(reservationRepository.findByBookingId(1L))
                .thenReturn(reservation);

        Booking confirmed = bookingService.confirmBooking(1L);

        assertEquals(BookingStatus.CONFIRMED, confirmed.getStatus());
        verify(reservationRepository).save(any(Reservation.class));
    }

    // ❌ TEST 5 — Cancel already cancelled booking
    @Test
    void cancelBooking_alreadyCancelled_shouldFail() {
        booking.setBookingId(1L);
        booking.setStatus(BookingStatus.CANCELLED);

        when(bookingRepository.findById(1L))
                .thenReturn(java.util.Optional.of(booking));

        assertThrows(
                BadRequestException.class,
                () -> bookingService.cancelBooking(1L)
        );
    }

    // ❌ TEST 6 — Booking not found
    @Test
    void getBookingById_notFound_shouldFail() {
        when(bookingRepository.findById(99L))
                .thenReturn(java.util.Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> bookingService.getBookingById(99L)
        );
    }
}