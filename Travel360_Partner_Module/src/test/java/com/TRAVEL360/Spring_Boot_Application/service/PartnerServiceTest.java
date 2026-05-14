package com.TRAVEL360.Spring_Boot_Application.service;

import com.TRAVEL360.Spring_Boot_Application.entity.Partner;
import com.TRAVEL360.Spring_Boot_Application.entity.enums.PartnerStatus;
import com.TRAVEL360.Spring_Boot_Application.entity.enums.PartnerType;
import com.TRAVEL360.Spring_Boot_Application.repository.PartnerRepository;
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
class PartnerServiceTest {

    @Mock
    private PartnerRepository partnerRepository;

    @InjectMocks
    private PartnerService partnerService;

    private Partner partner;

    @BeforeEach
    void setUp() {
        partner = new Partner();
        partner.setPartnerId(1L);
        partner.setName("Test Partner");
        partner.setType(PartnerType.AIRLINE);
        partner.setContactEmail("test@example.com");
        partner.setContactPhone("1234567890");
        partner.setStatus(PartnerStatus.ACTIVE);
    }

    @Test
    void testSavePartner() {
        when(partnerRepository.save(any(Partner.class))).thenReturn(partner);
        Partner saved = partnerService.savePartner(partner);
        assertNotNull(saved);
        assertEquals("Test Partner", saved.getName());
        verify(partnerRepository, times(1)).save(partner);
    }

    @Test
    void testGetAllPartners() {
        when(partnerRepository.findAll()).thenReturn(Arrays.asList(partner));
        List<Partner> partners = partnerService.getAllPartners();
        assertEquals(1, partners.size());
        verify(partnerRepository, times(1)).findAll();
    }

    @Test
    void testGetPartnerById() {
        when(partnerRepository.findById(1L)).thenReturn(Optional.of(partner));
        Optional<Partner> result = partnerService.getPartnerById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getPartnerId());
    }

    @Test
    void testGetPartnerById_NotFound() {
        when(partnerRepository.findById(99L)).thenReturn(Optional.empty());
        Optional<Partner> result = partnerService.getPartnerById(99L);
        assertFalse(result.isPresent());
    }

    @Test
    void testUpdatePartner() {
        Partner updated = new Partner();
        updated.setName("Updated Partner");
        updated.setType(PartnerType.HOTEL);
        updated.setContactEmail("updated@example.com");
        updated.setContactPhone("9876543210");
        updated.setStatus(PartnerStatus.INACTIVE);

        when(partnerRepository.findById(1L)).thenReturn(Optional.of(partner));
        when(partnerRepository.save(any(Partner.class))).thenReturn(partner);

        Partner result = partnerService.updatePartner(1L, updated);
        assertNotNull(result);
        verify(partnerRepository, times(1)).save(any(Partner.class));
    }

    @Test
    void testUpdatePartner_NotFound() {
        when(partnerRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> partnerService.updatePartner(99L, partner));
    }
}

