package org.example.travel360.mapper;

import org.example.travel360.dto.BookingMinimalDTO;
import org.example.travel360.dto.ItineraryDTO;
import org.example.travel360.dto.ReservationMinimalDTO;
import org.example.travel360.model.Booking;
import org.example.travel360.model.Itinerary;
import org.example.travel360.model.Reservation;
import org.springframework.stereotype.Component;

@Component
public class EntityDTOMapper {

    //convert the itinerary to ItineraryDTO
    public ItineraryDTO toItineraryDTO(Itinerary itinerary) {
        if (itinerary == null) {
            return null;
        }
        return new ItineraryDTO(
                itinerary.getItineraryId(),
                itinerary.getUserId(),
                itinerary.getStartDate(),
                itinerary.getEndDate(),
                itinerary.getStatus()
        );
    }


    //convert the booking to bookingDTO
    public BookingMinimalDTO toBookingMinimalDTO(Booking booking) {
        if (booking == null) {
            return null;
        }
        return new BookingMinimalDTO(
                booking.getBookingId(),
                booking.getUserId(),
                booking.getStatus()
        );
    }


    //convert the reservation to ReservationDTO
    public ReservationMinimalDTO toReservationMinimalDTO(Reservation reservation) {
        if (reservation == null) {
            return null;
        }
        return new ReservationMinimalDTO(
                reservation.getStartDate(),
                reservation.getEndDate()
        );
    }
}

