package com.TRAVEL360.Spring_Boot_Application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.TRAVEL360.Spring_Boot_Application.entity.HotelInventory;
import com.TRAVEL360.Spring_Boot_Application.repository.HotelInventoryRepository;

@Service
public class HotelInventoryService {

    private final HotelInventoryRepository hotelInventoryRepository;

    public HotelInventoryService(
            HotelInventoryRepository hotelInventoryRepository) {
        this.hotelInventoryRepository = hotelInventoryRepository;
    }

    public HotelInventory saveHotel(HotelInventory hotel) {
        return hotelInventoryRepository.save(hotel);
    }

    public List<HotelInventory> getAllHotels() {
        return hotelInventoryRepository.findAll();
    }

    public Optional<HotelInventory> getHotelById(Long hotelId) {
        return hotelInventoryRepository.findById(hotelId);
    }

    public HotelInventory updateHotel(Long hotelId, HotelInventory updatedHotel) {
        return hotelInventoryRepository.findById(hotelId).map(existing -> {
            existing.setPartnerId(updatedHotel.getPartnerId());
            existing.setHotelName(updatedHotel.getHotelName());
            existing.setLocation(updatedHotel.getLocation());
            existing.setRoomType(updatedHotel.getRoomType());
            existing.setTotalRooms(updatedHotel.getTotalRooms());
            existing.setAvailableRooms(updatedHotel.getAvailableRooms());
            existing.setPricePerNight(updatedHotel.getPricePerNight());
            existing.setStatus(updatedHotel.getStatus());
            return hotelInventoryRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Hotel not found with id: " + hotelId));
    }
}
