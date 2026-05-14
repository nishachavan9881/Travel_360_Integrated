package org.vibhav.app.compliance_service.compliance.service;



import org.vibhav.app.compliance_service.compliance.entity.ComplianceReport;
import org.vibhav.app.compliance_service.compliance.repository.ComplianceReportRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComplianceServiceImpl implements ComplianceService {

    private final ComplianceReportRepository repo;

    public ComplianceServiceImpl(ComplianceReportRepository repo) {
        this.repo = repo;
    }

    @Override
    public ComplianceReport createReport(ComplianceReport report) {
        report.setGeneratedDate(LocalDateTime.now());
        return repo.save(report);
    }

    @Override
    public List<ComplianceReport> getAllReports() {
        return repo.findAll();
    }

    @Override
    public ComplianceReport getReportById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found"));
    }

    @Override
    public void deleteReport(Long id) {
        repo.deleteById(id);
    }
}