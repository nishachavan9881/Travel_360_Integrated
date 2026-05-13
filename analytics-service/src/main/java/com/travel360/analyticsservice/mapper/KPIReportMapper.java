package com.travel360.analyticsservice.mapper;

import com.travel360.analyticsservice.entity.KPIReport;
import com.travel360.analyticsservice.dto.KPIReportDTO;
import com.travel360.analyticsservice.dto.KPIReportResponseDTO;

import java.time.LocalDateTime;

public class KPIReportMapper {

    public static KPIReport toEntity(KPIReportDTO dto) {

        KPIReport report = new KPIReport();

        report.setScope(dto.getScope());
        report.setMetrics(dto.getMetrics());
        report.setGeneratedDate(LocalDateTime.now());

        return report;
    }

    public static KPIReportResponseDTO toResponseDTO(KPIReport report) {

        KPIReportResponseDTO dto = new KPIReportResponseDTO();

        dto.setReportId(report.getReportId());
        dto.setScope(report.getScope());
        dto.setMetrics(report.getMetrics());
        dto.setGeneratedDate(report.getGeneratedDate());

        return dto;
    }
}
