package com.TRAVEL360.Spring_Boot_Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.TRAVEL360.Spring_Boot_Application.entity.Partner;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
}
