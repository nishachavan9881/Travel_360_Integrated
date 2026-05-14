package org.example.travel360.controller;

import org.example.travel360.dto.ItineraryDTO;
import org.example.travel360.service.ItineraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Logger;


@RestController
@RequestMapping("/api/itinerary")
@CrossOrigin(origins = "*")
public class ItineraryRestController {

    private static final Logger logger = Logger.getLogger(ItineraryRestController.class.getName());
    private final ItineraryService itineraryService;


    public ItineraryRestController(ItineraryService itineraryService) {

        this.itineraryService = itineraryService;
    }


    //add the new data to itinerary table based on userId
    @PostMapping("/generate/{userId}")
    public ResponseEntity<?> generateItinerary(@PathVariable Integer userId) {
        logger.info("Received request to generate itinerary for userId: " + userId);
        ItineraryDTO itineraryDTO = itineraryService.generateItinerary(userId);
        logger.info("Itinerary generated successfully for userId: " + userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(itineraryDTO);//201
    }


    //get the itineary detail based on the userId
    @GetMapping("/{userId}")
    public ResponseEntity<?> getItinerary(@PathVariable Integer userId) {
        logger.info("Received request to retrieve itinerary for userId: " + userId);
        ItineraryDTO itineraryDTO = itineraryService.getItineraryByUserId(userId);
        logger.info("Itinerary retrieved successfully for userId: " + userId);
        return ResponseEntity.ok(itineraryDTO);
    }


    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Itinerary API is running!");
    }
}



