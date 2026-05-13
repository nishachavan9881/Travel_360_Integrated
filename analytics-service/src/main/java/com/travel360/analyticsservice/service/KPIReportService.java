package com.travel360.analyticsservice.service;

import com.travel360.analyticsservice.entity.KPIReport;
import java.util.List;

public interface KPIReportService {

    KPIReport createReport(KPIReport report);

    List<KPIReport> getAllReports();

    KPIReport getReportById(Long id);

    void deleteReport(Long id);
}
