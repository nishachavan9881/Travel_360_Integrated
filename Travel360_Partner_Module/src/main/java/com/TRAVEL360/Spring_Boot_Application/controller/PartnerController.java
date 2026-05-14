package com.TRAVEL360.Spring_Boot_Application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.TRAVEL360.Spring_Boot_Application.entity.Partner;
import com.TRAVEL360.Spring_Boot_Application.service.PartnerService;
import com.TRAVEL360.Spring_Boot_Application.dto.PartnerDTO;

@RestController
@RequestMapping("/api/partners")
public class PartnerController {

    private final PartnerService partnerService;

    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @PostMapping
    public Partner createPartner(@RequestBody PartnerDTO dto) {

        Partner partner = new Partner();
        partner.setName(dto.getName());
        partner.setType(dto.getType());
        partner.setContactEmail(dto.getContactEmail());
        partner.setContactPhone(dto.getContactPhone());
        partner.setStatus(dto.getStatus());

        return partnerService.savePartner(partner);
    }

    @GetMapping
    public List<Partner> getAllPartners() {
        return partnerService.getAllPartners();
    }

    @GetMapping("/{id}")
    public Optional<Partner> getPartnerById(@PathVariable Long id) {
        return partnerService.getPartnerById(id);
    }

    @PutMapping("/{id}")
    public Partner updatePartner(@PathVariable Long id, @RequestBody PartnerDTO dto) {
        Partner partner = new Partner();
        partner.setName(dto.getName());
        partner.setType(dto.getType());
        partner.setContactEmail(dto.getContactEmail());
        partner.setContactPhone(dto.getContactPhone());
        partner.setStatus(dto.getStatus());
        return partnerService.updatePartner(id, partner);
    }
}
