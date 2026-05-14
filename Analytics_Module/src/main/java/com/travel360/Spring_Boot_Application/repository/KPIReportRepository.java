package com.travel360.Spring_Boot_Application.repository;

import com.travel360.Spring_Boot_Application.entity.KPIReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KPIReportRepository extends JpaRepository<KPIReport, Long> {
}