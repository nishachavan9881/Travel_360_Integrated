package com.TRAVEL360.Spring_Boot_Application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.TRAVEL360.Spring_Boot_Application.entity.FlightInventory;
import com.TRAVEL360.Spring_Boot_Application.service.FlightInventoryService;
import com.TRAVEL360.Spring_Boot_Application.dto.FlightInventoryDTO;

@RestController
@RequestMapping("/api/flights")
public class FlightInventoryController {

    private final FlightInventoryService flightInventoryService;

    public FlightInventoryController(
            FlightInventoryService flightInventoryService) {
        this.flightInventoryService = flightInventoryService;
    }

    @PostMapping
    public FlightInventory createFlight(@RequestBody FlightInventoryDTO dto) {

        FlightInventory flight = new FlightInventory();
        flight.setPartnerId(dto.getPartnerId());
        flight.setFlightNumber(dto.getFlightNumber());
        flight.setSource(dto.getSource());
        flight.setDestination(dto.getDestination());
        flight.setTravelDate(dto.getTravelDate());
        flight.setDepartureTime(dto.getDepartureTime());
        flight.setArrivalTime(dto.getArrivalTime());
        flight.setTotalSeats(dto.getTotalSeats());
        flight.setAvailableSeats(dto.getAvailableSeats());
        flight.setPrice(dto.getPrice());
        flight.setStatus(dto.getStatus());

        return flightInventoryService.saveFlight(flight);
    }

    @GetMapping
    public List<FlightInventory> getAllFlights() {
        return flightInventoryService.getAllFlights();
    }

    @GetMapping("/{id}")
    public Optional<FlightInventory> getFlightById(@PathVariable Long id) {
        return flightInventoryService.getFlightById(id);
    }

    @PutMapping("/{id}")
    public FlightInventory updateFlight(@PathVariable Long id, @RequestBody FlightInventoryDTO dto) {
        FlightInventory flight = new FlightInventory();
        flight.setPartnerId(dto.getPartnerId());
        flight.setFlightNumber(dto.getFlightNumber());
        flight.setSource(dto.getSource());
        flight.setDestination(dto.getDestination());
        flight.setTravelDate(dto.getTravelDate());
        flight.setDepartureTime(dto.getDepartureTime());
        flight.setArrivalTime(dto.getArrivalTime());
        flight.setTotalSeats(dto.getTotalSeats());
        flight.setAvailableSeats(dto.getAvailableSeats());
        flight.setPrice(dto.getPrice());
        flight.setStatus(dto.getStatus());
        return flightInventoryService.updateFlight(id, flight);
    }
}