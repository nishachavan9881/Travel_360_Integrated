package com.TRAVEL360.Spring_Boot_Application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.TRAVEL360.Spring_Boot_Application.entity.FlightInventory;
import com.TRAVEL360.Spring_Boot_Application.repository.FlightInventoryRepository;

@Service
public class FlightInventoryService {

    private final FlightInventoryRepository flightInventoryRepository;

    public FlightInventoryService(
            FlightInventoryRepository flightInventoryRepository) {
        this.flightInventoryRepository = flightInventoryRepository;
    }

    public FlightInventory saveFlight(FlightInventory flight) {
        return flightInventoryRepository.save(flight);
    }

    public List<FlightInventory> getAllFlights() {
        return flightInventoryRepository.findAll();
    }

    public Optional<FlightInventory> getFlightById(Long flightId) {
        return flightInventoryRepository.findById(flightId);
    }

    public FlightInventory updateFlight(Long flightId, FlightInventory updatedFlight) {
        return flightInventoryRepository.findById(flightId).map(existing -> {
            existing.setPartnerId(updatedFlight.getPartnerId());
            existing.setFlightNumber(updatedFlight.getFlightNumber());
            existing.setSource(updatedFlight.getSource());
            existing.setDestination(updatedFlight.getDestination());
            existing.setTravelDate(updatedFlight.getTravelDate());
            existing.setDepartureTime(updatedFlight.getDepartureTime());
            existing.setArrivalTime(updatedFlight.getArrivalTime());
            existing.setTotalSeats(updatedFlight.getTotalSeats());
            existing.setAvailableSeats(updatedFlight.getAvailableSeats());
            existing.setPrice(updatedFlight.getPrice());
            existing.setStatus(updatedFlight.getStatus());
            return flightInventoryRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Flight not found with id: " + flightId));
    }
}
