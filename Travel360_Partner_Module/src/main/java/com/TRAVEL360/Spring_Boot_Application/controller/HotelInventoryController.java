package com.TRAVEL360.Spring_Boot_Application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.TRAVEL360.Spring_Boot_Application.entity.HotelInventory;
import com.TRAVEL360.Spring_Boot_Application.service.HotelInventoryService;
import com.TRAVEL360.Spring_Boot_Application.dto.HotelInventoryDTO;

@RestController
@RequestMapping("/api/hotels")
public class HotelInventoryController {

    private final HotelInventoryService hotelInventoryService;

    public HotelInventoryController(
            HotelInventoryService hotelInventoryService) {
        this.hotelInventoryService = hotelInventoryService;
    }

    @PostMapping
    public HotelInventory createHotel(@RequestBody HotelInventoryDTO dto) {

        HotelInventory hotel = new HotelInventory();
        hotel.setPartnerId(dto.getPartnerId());
        hotel.setHotelName(dto.getHotelName());
        hotel.setLocation(dto.getLocation());
        hotel.setRoomType(dto.getRoomType());
        hotel.setTotalRooms(dto.getTotalRooms());
        hotel.setAvailableRooms(dto.getAvailableRooms());
        hotel.setPricePerNight(dto.getPricePerNight());
        hotel.setStatus(dto.getStatus());

        return hotelInventoryService.saveHotel(hotel);
    }

    @GetMapping
    public List<HotelInventory> getAllHotels() {
        return hotelInventoryService.getAllHotels();
    }

    @GetMapping("/{id}")
    public Optional<HotelInventory> getHotelById(@PathVariable Long id) {
        return hotelInventoryService.getHotelById(id);
    }

    @PutMapping("/{id}")
    public HotelInventory updateHotel(@PathVariable Long id, @RequestBody HotelInventoryDTO dto) {
        HotelInventory hotel = new HotelInventory();
        hotel.setPartnerId(dto.getPartnerId());
        hotel.setHotelName(dto.getHotelName());
        hotel.setLocation(dto.getLocation());
        hotel.setRoomType(dto.getRoomType());
        hotel.setTotalRooms(dto.getTotalRooms());
        hotel.setAvailableRooms(dto.getAvailableRooms());
        hotel.setPricePerNight(dto.getPricePerNight());
        hotel.setStatus(dto.getStatus());
        return hotelInventoryService.updateHotel(id, hotel);
    }
}