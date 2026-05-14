package com.travel360.Spring_Boot_Application.service;

import com.travel360.Spring_Boot_Application.entity.KPIReport;
import com.travel360.Spring_Boot_Application.repository.KPIReportRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class KPIReportServiceImpl implements KPIReportService {

    private final KPIReportRepository repository;

    public KPIReportServiceImpl(KPIReportRepository repository) {
        this.repository = repository;
    }

    @Override
    public KPIReport createReport(KPIReport report) {
        report.setGeneratedDate(LocalDateTime.now());
        return repository.save(report);
    }

    @Override
    public List<KPIReport> getAllReports() {
        return repository.findAll();
    }

    @Override
    public KPIReport getReportById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("KPI Report not found"));
    }

    @Override
    public void deleteReport(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("KPI Report not found");
        }
        repository.deleteById(id);
    }
}
