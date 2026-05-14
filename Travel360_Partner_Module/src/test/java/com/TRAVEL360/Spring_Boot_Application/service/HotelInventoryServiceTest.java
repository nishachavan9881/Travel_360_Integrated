package com.TRAVEL360.Spring_Boot_Application.service;

import com.TRAVEL360.Spring_Boot_Application.entity.HotelInventory;
import com.TRAVEL360.Spring_Boot_Application.entity.enums.HotelStatus;
import com.TRAVEL360.Spring_Boot_Application.entity.enums.RoomType;
import com.TRAVEL360.Spring_Boot_Application.repository.HotelInventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HotelInventoryServiceTest {

    @Mock
    private HotelInventoryRepository hotelInventoryRepository;

    @InjectMocks
    private HotelInventoryService hotelInventoryService;

    private HotelInventory hotel;

    @BeforeEach
    void setUp() {
        hotel = new HotelInventory();
        hotel.setHotelId(1L);
        hotel.setPartnerId(10L);
        hotel.setHotelName("Grand Palace");
        hotel.setLocation("Delhi");
        hotel.setRoomType(RoomType.DELUXE);
        hotel.setTotalRooms(100);
        hotel.setAvailableRooms(50);
        hotel.setPricePerNight(3000.0);
        hotel.setStatus(HotelStatus.ACTIVE);
    }

    @Test
    void testSaveHotel() {
        when(hotelInventoryRepository.save(any(HotelInventory.class))).thenReturn(hotel);
        HotelInventory saved = hotelInventoryService.saveHotel(hotel);
        assertNotNull(saved);
        assertEquals("Grand Palace", saved.getHotelName());
        verify(hotelInventoryRepository, times(1)).save(hotel);
    }

    @Test
    void testGetAllHotels() {
        when(hotelInventoryRepository.findAll()).thenReturn(Arrays.asList(hotel));
        List<HotelInventory> hotels = hotelInventoryService.getAllHotels();
        assertEquals(1, hotels.size());
        verify(hotelInventoryRepository, times(1)).findAll();
    }

    @Test
    void testGetHotelById() {
        when(hotelInventoryRepository.findById(1L)).thenReturn(Optional.of(hotel));
        Optional<HotelInventory> result = hotelInventoryService.getHotelById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getHotelId());
    }

    @Test
    void testGetHotelById_NotFound() {
        when(hotelInventoryRepository.findById(99L)).thenReturn(Optional.empty());
        Optional<HotelInventory> result = hotelInventoryService.getHotelById(99L);
        assertFalse(result.isPresent());
    }

    @Test
    void testUpdateHotel() {
        HotelInventory updated = new HotelInventory();
        updated.setPartnerId(10L);
        updated.setHotelName("Royal Inn");
        updated.setLocation("Mumbai");
        updated.setRoomType(RoomType.SUITE);
        updated.setTotalRooms(80);
        updated.setAvailableRooms(30);
        updated.setPricePerNight(5000.0);
        updated.setStatus(HotelStatus.ACTIVE);

        when(hotelInventoryRepository.findById(1L)).thenReturn(Optional.of(hotel));
        when(hotelInventoryRepository.save(any(HotelInventory.class))).thenReturn(hotel);

        HotelInventory result = hotelInventoryService.updateHotel(1L, updated);
        assertNotNull(result);
        verify(hotelInventoryRepository, times(1)).save(any(HotelInventory.class));
    }

    @Test
    void testUpdateHotel_NotFound() {
        when(hotelInventoryRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> hotelInventoryService.updateHotel(99L, hotel));
    }
}

