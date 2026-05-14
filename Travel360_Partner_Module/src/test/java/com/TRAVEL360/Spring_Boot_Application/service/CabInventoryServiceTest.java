package com.TRAVEL360.Spring_Boot_Application.service;

import com.TRAVEL360.Spring_Boot_Application.entity.CabInventory;
import com.TRAVEL360.Spring_Boot_Application.entity.enums.CabStatus;
import com.TRAVEL360.Spring_Boot_Application.entity.enums.VehicleType;
import com.TRAVEL360.Spring_Boot_Application.repository.CabInventoryRepository;
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
class CabInventoryServiceTest {

    @Mock
    private CabInventoryRepository cabInventoryRepository;

    @InjectMocks
    private CabInventoryService cabInventoryService;

    private CabInventory cab;

    @BeforeEach
    void setUp() {
        cab = new CabInventory();
        cab.setCabId(1L);
        cab.setPartnerId(10L);
        cab.setVehicleType(VehicleType.SEDAN);
        cab.setCarNumber("DL01AB1234");
        cab.setDriverName("Ramesh");
        cab.setDriverPhone("9876543210");
        cab.setSource("Delhi");
        cab.setDestination("Noida");
        cab.setAvailableUnits(5);
        cab.setPricePerKm(12.0);
        cab.setBaseFare(50.0);
        cab.setStatus(CabStatus.ACTIVE);
    }

    @Test
    void testSaveCab() {
        when(cabInventoryRepository.save(any(CabInventory.class))).thenReturn(cab);
        CabInventory saved = cabInventoryService.saveCab(cab);
        assertNotNull(saved);
        assertEquals("DL01AB1234", saved.getCarNumber());
        verify(cabInventoryRepository, times(1)).save(cab);
    }

    @Test
    void testGetAllCabs() {
        when(cabInventoryRepository.findAll()).thenReturn(Arrays.asList(cab));
        List<CabInventory> cabs = cabInventoryService.getAllCabs();
        assertEquals(1, cabs.size());
        verify(cabInventoryRepository, times(1)).findAll();
    }

    @Test
    void testGetCabById() {
        when(cabInventoryRepository.findById(1L)).thenReturn(Optional.of(cab));
        Optional<CabInventory> result = cabInventoryService.getCabById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getCabId());
    }

    @Test
    void testGetCabById_NotFound() {
        when(cabInventoryRepository.findById(99L)).thenReturn(Optional.empty());
        Optional<CabInventory> result = cabInventoryService.getCabById(99L);
        assertFalse(result.isPresent());
    }

    @Test
    void testUpdateCab() {
        CabInventory updated = new CabInventory();
        updated.setPartnerId(10L);
        updated.setVehicleType(VehicleType.SUV);
        updated.setCarNumber("MH02CD5678");
        updated.setDriverName("Suresh");
        updated.setDriverPhone("8765432109");
        updated.setSource("Mumbai");
        updated.setDestination("Pune");
        updated.setAvailableUnits(3);
        updated.setPricePerKm(15.0);
        updated.setBaseFare(80.0);
        updated.setStatus(CabStatus.ACTIVE);

        when(cabInventoryRepository.findById(1L)).thenReturn(Optional.of(cab));
        when(cabInventoryRepository.save(any(CabInventory.class))).thenReturn(cab);

        CabInventory result = cabInventoryService.updateCab(1L, updated);
        assertNotNull(result);
        verify(cabInventoryRepository, times(1)).save(any(CabInventory.class));
    }

    @Test
    void testUpdateCab_NotFound() {
        when(cabInventoryRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> cabInventoryService.updateCab(99L, cab));
    }
}

