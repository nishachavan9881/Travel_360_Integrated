package com.TRAVEL360.Spring_Boot_Application.service;

import com.TRAVEL360.Spring_Boot_Application.entity.FlightInventory;
import com.TRAVEL360.Spring_Boot_Application.entity.enums.FlightStatus;
import com.TRAVEL360.Spring_Boot_Application.repository.FlightInventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightInventoryServiceTest {

    @Mock
    private FlightInventoryRepository flightInventoryRepository;

    @InjectMocks
    private FlightInventoryService flightInventoryService;

    private FlightInventory flight;

    @BeforeEach
    void setUp() {
        flight = new FlightInventory();
        flight.setFlightId(1L);
        flight.setPartnerId(10L);
        flight.setFlightNumber("AI101");
        flight.setSource("Delhi");
        flight.setDestination("Mumbai");
        flight.setTravelDate(LocalDate.of(2026, 6, 15));
        flight.setDepartureTime(LocalTime.of(10, 0));
        flight.setArrivalTime(LocalTime.of(12, 0));
        flight.setTotalSeats(200);
        flight.setAvailableSeats(100);
        flight.setPrice(5000.0);
        flight.setStatus(FlightStatus.ACTIVE);
    }

    @Test
    void testSaveFlight() {
        when(flightInventoryRepository.save(any(FlightInventory.class))).thenReturn(flight);
        FlightInventory saved = flightInventoryService.saveFlight(flight);
        assertNotNull(saved);
        assertEquals("AI101", saved.getFlightNumber());
        verify(flightInventoryRepository, times(1)).save(flight);
    }

    @Test
    void testGetAllFlights() {
        when(flightInventoryRepository.findAll()).thenReturn(Arrays.asList(flight));
        List<FlightInventory> flights = flightInventoryService.getAllFlights();
        assertEquals(1, flights.size());
        verify(flightInventoryRepository, times(1)).findAll();
    }

    @Test
    void testGetFlightById() {
        when(flightInventoryRepository.findById(1L)).thenReturn(Optional.of(flight));
        Optional<FlightInventory> result = flightInventoryService.getFlightById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getFlightId());
    }

    @Test
    void testGetFlightById_NotFound() {
        when(flightInventoryRepository.findById(99L)).thenReturn(Optional.empty());
        Optional<FlightInventory> result = flightInventoryService.getFlightById(99L);
        assertFalse(result.isPresent());
    }

    @Test
    void testUpdateFlight() {
        FlightInventory updated = new FlightInventory();
        updated.setPartnerId(10L);
        updated.setFlightNumber("AI202");
        updated.setSource("Bangalore");
        updated.setDestination("Chennai");
        updated.setTravelDate(LocalDate.of(2026, 7, 1));
        updated.setDepartureTime(LocalTime.of(8, 0));
        updated.setArrivalTime(LocalTime.of(9, 30));
        updated.setTotalSeats(150);
        updated.setAvailableSeats(50);
        updated.setPrice(3000.0);
        updated.setStatus(FlightStatus.ACTIVE);

        when(flightInventoryRepository.findById(1L)).thenReturn(Optional.of(flight));
        when(flightInventoryRepository.save(any(FlightInventory.class))).thenReturn(flight);

        FlightInventory result = flightInventoryService.updateFlight(1L, updated);
        assertNotNull(result);
        verify(flightInventoryRepository, times(1)).save(any(FlightInventory.class));
    }

    @Test
    void testUpdateFlight_NotFound() {
        when(flightInventoryRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> flightInventoryService.updateFlight(99L, flight));
    }
}

