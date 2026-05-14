package org.example.travel360.service;

import org.example.travel360.dto.BookingMinimalDTO;
import org.example.travel360.dto.ItineraryDTO;
import org.example.travel360.dto.ReservationMinimalDTO;
import org.example.travel360.exception.*;
import org.example.travel360.mapper.EntityDTOMapper;
import org.example.travel360.model.Booking;
import org.example.travel360.model.Reservation;
import org.example.travel360.model.Itinerary;
import org.example.travel360.repository.BookingRepository;
import org.example.travel360.repository.ReservationRepository;
import org.example.travel360.repository.ItineraryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ItineraryService {

    private static final Logger logger = Logger.getLogger(ItineraryService.class.getName());


    private final BookingRepository bookingRepository;
    private final ReservationRepository reservationRepository;
    private final ItineraryRepository itineraryRepository;
    private final EntityDTOMapper entityDTOMapper;

//     Constructor Injection (Spring Best Practice)
//     Recommended over @Autowired field injection
     public ItineraryService(BookingRepository bookingRepository,
                            ReservationRepository reservationRepository,
                            ItineraryRepository itineraryRepository,
                            EntityDTOMapper entityDTOMapper) {
        this.bookingRepository = bookingRepository;
        this.reservationRepository = reservationRepository;
        this.itineraryRepository = itineraryRepository;
        this.entityDTOMapper = entityDTOMapper;
    }



    public ItineraryDTO generateItinerary(Integer userId) {
        // Validate userId
        if (userId == null || userId <= 0) {
            logger.severe("Invalid userId provided: " + userId);
            throw new InvalidUserIdException("User ID must be a positive number");
        }

        // fetch the booking by client entered user id
        Optional<Booking> bookingOptional = bookingRepository.findByUserId(userId);

        //check exists or not
        if (bookingOptional.isEmpty()) {
            logger.severe("Booking not found for User ID: " + userId);
            throw new BookingNotFoundException("Booking not found for User ID: " + userId);
        }

        //if exists get the booking
        Booking booking = bookingOptional.get();

        //get the required field in bookingDTO from booking
        BookingMinimalDTO bookingDTO = entityDTOMapper.toBookingMinimalDTO(booking);

        //check booking is confirmed or not
        if (!"CONFIRMED".equalsIgnoreCase(bookingDTO.getStatus())) {
            logger.severe("Booking is not CONFIRMED. Current status: " + bookingDTO.getStatus());
            throw new InvalidBookingStatusException("Booking is not CONFIRMED. Current status: " + bookingDTO.getStatus());
        }

        // Fetch reservation using bookingId
        Optional<Reservation> reservationOptional =
                reservationRepository.findByBookingId(bookingDTO.getBookingId());

        // check reservation exists or not
        if (reservationOptional.isEmpty()) {
            logger.severe("Reservation not found for Booking ID: " + bookingDTO.getBookingId());
            throw new ReservationNotFoundException("Reservation not found for Booking ID: " + bookingDTO.getBookingId());
        }

        //if exists get the reservation
        Reservation reservation = reservationOptional.get();

        //get the required field in reservationDTO from reservation
        ReservationMinimalDTO reservationDTO = entityDTOMapper.toReservationMinimalDTO(reservation);

         // Display itinerary details in terminal
         System.out.println("\n╔════════════════════════════════════════╗");
         System.out.println("║        ITINERARY DETAILS                ║");
         System.out.println("╠════════════════════════════════════════╣");
         System.out.println("║ User ID       : " + padRight(String.valueOf(bookingDTO.getUserId()), 20) + "║");
         System.out.println("║ Booking ID    : " + padRight(String.valueOf(bookingDTO.getBookingId()), 20) + "║");
         System.out.println("║ Start Date    : " + padRight(String.valueOf(reservationDTO.getStartDate()), 20) + "║");
         System.out.println("║ End Date      : " + padRight(String.valueOf(reservationDTO.getEndDate()), 20) + "║");
         System.out.println("║ Booking Status: " + padRight(bookingDTO.getStatus(), 20) + "║");
         System.out.println("╚════════════════════════════════════════╝\n");

         logger.info("Itinerary Details - User ID: " + bookingDTO.getUserId());
         logger.info("Booking ID: " + bookingDTO.getBookingId() + ", Start: " + reservationDTO.getStartDate() + ", End: " + reservationDTO.getEndDate());

         // CHECK IF ITINERARY ALREADY EXISTS (Prevents Duplicates)
         List<Itinerary> existingItineraries = itineraryRepository.findByUserId(userId);

         Itinerary itinerary;
         boolean isNewRecord = false;

         //check the itineraries are present in the itinerary or not
         if (!existingItineraries.isEmpty()) {
             // UPDATE existing record (take the first one if multiple exist)
             itinerary = existingItineraries.getFirst();
             System.out.println("⚡ UPDATING existing itinerary record...");
             logger.info("UPDATING existing itinerary record...");
         } else {
             // CREATE new record
             itinerary = new Itinerary();
             isNewRecord = true;
             System.out.println("✨ CREATING new itinerary record...");
             logger.info("CREATING new itinerary record...");
         }

         // Set/Update fields (same for both new and existing)
         itinerary.setUserId(bookingDTO.getUserId());
         itinerary.setStartDate(reservationDTO.getStartDate());
         itinerary.setEndDate(reservationDTO.getEndDate());
         itinerary.setStatus(bookingDTO.getStatus());

         // Save itinerary record to database
         Itinerary savedItinerary = itineraryRepository.save(itinerary);

         //convert the saved entity to the itinerary DTO
         ItineraryDTO itineraryDTO = entityDTOMapper.toItineraryDTO(savedItinerary);

         if (isNewRecord) {
             System.out.println("✅ NEW itinerary saved successfully in database");
             logger.info("NEW itinerary saved successfully in database");
         } else {
             System.out.println("✅ Itinerary UPDATED successfully in database");
             logger.info("Itinerary UPDATED successfully in database");
         }
         System.out.println("📌 Itinerary ID: " + itineraryDTO.getItineraryId() + "\n");
         logger.info("Itinerary ID: " + itineraryDTO.getItineraryId());

        return itineraryDTO;
    }

    // Retrieve itinerary by user ID
    public ItineraryDTO getItineraryByUserId(Integer userId) {
        // Validate userId
        if (userId == null || userId <= 0) {
            logger.severe("Invalid userId provided: " + userId);
            throw new InvalidUserIdException("User ID must be a positive number");
        }

        List<Itinerary> itineraries = itineraryRepository.findByUserId(userId);

        if (itineraries.isEmpty()) {
            logger.severe("Itinerary not found for User ID: " + userId);
            throw new ItineraryNotFoundException("Itinerary not found for User ID: " + userId);
        }

        logger.info("Itinerary retrieved successfully for User ID: " + userId);
        return entityDTOMapper.toItineraryDTO(itineraries.getFirst());
    }

    // Helper method to pad strings for formatted output
    private static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }
}