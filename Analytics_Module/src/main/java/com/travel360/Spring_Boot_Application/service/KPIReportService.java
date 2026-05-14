package com.travel360.Spring_Boot_Application.service;

import com.travel360.Spring_Boot_Application.entity.KPIReport;
import java.util.List;

public interface KPIReportService {

    KPIReport createReport(KPIReport report);

    List<KPIReport> getAllReports();

    KPIReport getReportById(Long id);

    void deleteReport(Long id);
}
