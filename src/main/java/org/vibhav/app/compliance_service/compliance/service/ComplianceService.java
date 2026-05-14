package org.vibhav.app.compliance_service.compliance.service;



import org.vibhav.app.compliance_service.compliance.entity.ComplianceReport;
import java.util.List;

public interface ComplianceService {

    ComplianceReport createReport(ComplianceReport report);

    List<ComplianceReport> getAllReports();

    ComplianceReport getReportById(Long id);

    void deleteReport(Long id);
}

