package com.TRAVEL360.Spring_Boot_Application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.TRAVEL360.Spring_Boot_Application.entity.Partner;
import com.TRAVEL360.Spring_Boot_Application.repository.PartnerRepository;

@Service
public class PartnerService {

    private final PartnerRepository partnerRepository;

    public PartnerService(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    public Partner savePartner(Partner partner) {
        return partnerRepository.save(partner);
    }

    public List<Partner> getAllPartners() {
        return partnerRepository.findAll();
    }

    public Optional<Partner> getPartnerById(Long partnerId) {
        return partnerRepository.findById(partnerId);
    }

    public Partner updatePartner(Long partnerId, Partner updatedPartner) {
        return partnerRepository.findById(partnerId).map(existing -> {
            existing.setName(updatedPartner.getName());
            existing.setType(updatedPartner.getType());
            existing.setContactEmail(updatedPartner.getContactEmail());
            existing.setContactPhone(updatedPartner.getContactPhone());
            existing.setStatus(updatedPartner.getStatus());
            return partnerRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Partner not found with id: " + partnerId));
    }
}
