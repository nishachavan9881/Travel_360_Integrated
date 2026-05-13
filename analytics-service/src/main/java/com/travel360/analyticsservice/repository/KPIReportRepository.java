package com.travel360.analyticsservice.repository;

import com.travel360.analyticsservice.entity.KPIReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KPIReportRepository extends JpaRepository<KPIReport, Long> {
}