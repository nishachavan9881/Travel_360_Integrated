package org.vibhav.app.compliance_service.compliance.repository;



import org.vibhav.app.compliance_service.compliance.entity.ComplianceReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplianceReportRepository extends JpaRepository<ComplianceReport, Long> {
}
