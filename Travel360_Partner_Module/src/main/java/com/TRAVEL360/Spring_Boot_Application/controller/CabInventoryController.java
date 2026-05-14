package com.TRAVEL360.Spring_Boot_Application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.TRAVEL360.Spring_Boot_Application.entity.CabInventory;
import com.TRAVEL360.Spring_Boot_Application.service.CabInventoryService;
import com.TRAVEL360.Spring_Boot_Application.dto.CabInventoryDTO;

@RestController
@RequestMapping("/api/cabs")
public class CabInventoryController {

    private final CabInventoryService cabInventoryService;
    public CabInventoryController(
            CabInventoryService cabInventoryService) {
        this.cabInventoryService = cabInventoryService;
    }

    @PostMapping
    public CabInventory createCab(@RequestBody CabInventoryDTO dto) {

        CabInventory cab = new CabInventory();
        cab.setPartnerId(dto.getPartnerId());
        cab.setVehicleType(dto.getVehicleType());
        cab.setCarNumber(dto.getCarNumber());
        cab.setDriverName(dto.getDriverName());
        cab.setDriverPhone(dto.getDriverPhone());
        cab.setSource(dto.getSource());
        cab.setDestination(dto.getDestination());
        cab.setAvailableUnits(dto.getAvailableUnits());
        cab.setPricePerKm(dto.getPricePerKm());
        cab.setBaseFare(dto.getBaseFare());
        cab.setStatus(dto.getStatus());

        return cabInventoryService.saveCab(cab);
    }
    @GetMapping
    public List<CabInventory> getAllCabs() {
        return cabInventoryService.getAllCabs();
    }

    @GetMapping("/{id}")
    public Optional<CabInventory> getCabById(@PathVariable Long id) {
        return cabInventoryService.getCabById(id);
    }

    @PutMapping("/{id}")
    public CabInventory updateCab(@PathVariable Long id, @RequestBody CabInventoryDTO dto) {
        CabInventory cab = new CabInventory();
        cab.setPartnerId(dto.getPartnerId());
        cab.setVehicleType(dto.getVehicleType());
        cab.setCarNumber(dto.getCarNumber());
        cab.setDriverName(dto.getDriverName());
        cab.setDriverPhone(dto.getDriverPhone());
        cab.setSource(dto.getSource());
        cab.setDestination(dto.getDestination());
        cab.setAvailableUnits(dto.getAvailableUnits());
        cab.setPricePerKm(dto.getPricePerKm());
        cab.setBaseFare(dto.getBaseFare());
        cab.setStatus(dto.getStatus());
        return cabInventoryService.updateCab(id, cab);
    }
}
