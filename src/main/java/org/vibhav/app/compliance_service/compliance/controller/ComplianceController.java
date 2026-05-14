package org.vibhav.app.compliance_service.compliance.controller;



import org.vibhav.app.compliance_service.compliance.entity.ComplianceReport;
import org.vibhav.app.compliance_service.compliance.service.ComplianceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compliance")
public class ComplianceController {

    private final ComplianceService service;

    public ComplianceController(ComplianceService service) {
        this.service = service;
    }

    // ✅ Create Report
    @PostMapping
    public ComplianceReport createReport(@RequestBody ComplianceReport report) {
        return service.createReport(report);
    }

    // ✅ Get All Reports
    @GetMapping
    public List<ComplianceReport> getAllReports() {
        return service.getAllReports();
    }

    // ✅ Get By ID
    @GetMapping("/{id}")
    public ComplianceReport getReport(@PathVariable Long id) {
        return service.getReportById(id);
    }

    // ✅ Delete Report
    @DeleteMapping("/{id}")
    public String deleteReport(@PathVariable Long id) {
        service.deleteReport(id);
        return "Deleted Successfully";
    }
}
