package com.TRAVEL360.Spring_Boot_Application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.TRAVEL360.Spring_Boot_Application.entity.CabInventory;
import com.TRAVEL360.Spring_Boot_Application.repository.CabInventoryRepository;

@Service
public class CabInventoryService {

    private final CabInventoryRepository cabInventoryRepository;

    public CabInventoryService(
            CabInventoryRepository cabInventoryRepository) {
        this.cabInventoryRepository = cabInventoryRepository;
    }

    public CabInventory saveCab(CabInventory cab) {
        return cabInventoryRepository.save(cab);
    }

    public List<CabInventory> getAllCabs() {
        return cabInventoryRepository.findAll();
    }

    public Optional<CabInventory> getCabById(Long cabId) {
        return cabInventoryRepository.findById(cabId);
    }

    public CabInventory updateCab(Long cabId, CabInventory updatedCab) {
        return cabInventoryRepository.findById(cabId).map(existing -> {
            existing.setPartnerId(updatedCab.getPartnerId());
            existing.setVehicleType(updatedCab.getVehicleType());
            existing.setCarNumber(updatedCab.getCarNumber());
            existing.setDriverName(updatedCab.getDriverName());
            existing.setDriverPhone(updatedCab.getDriverPhone());
            existing.setSource(updatedCab.getSource());
            existing.setDestination(updatedCab.getDestination());
            existing.setAvailableUnits(updatedCab.getAvailableUnits());
            existing.setPricePerKm(updatedCab.getPricePerKm());
            existing.setBaseFare(updatedCab.getBaseFare());
            existing.setStatus(updatedCab.getStatus());
            return cabInventoryRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Cab not found with id: " + cabId));
    }
}
