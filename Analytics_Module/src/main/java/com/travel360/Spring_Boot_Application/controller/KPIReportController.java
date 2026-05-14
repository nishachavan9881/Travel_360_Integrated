
package com.travel360.Spring_Boot_Application.controller;

import com.travel360.Spring_Boot_Application.dto.KPIReportDTO;
import com.travel360.Spring_Boot_Application.dto.KPIReportResponseDTO;
import com.travel360.Spring_Boot_Application.entity.KPIReport;


import com.travel360.Spring_Boot_Application.mapper.KPIReportMapper;
import com.travel360.Spring_Boot_Application.service.KPIReportService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/kpi")
public class KPIReportController {

    private final KPIReportService service;

    public KPIReportController(KPIReportService service) {
        this.service = service;
    }

    @PostMapping
    public KPIReportResponseDTO create(@Valid @RequestBody KPIReportDTO dto) {
        KPIReport report = KPIReportMapper.toEntity(dto);
        KPIReport saved = service.createReport(report);
        return KPIReportMapper.toResponseDTO(saved);
    }

    @GetMapping
    public List<KPIReportResponseDTO> getAll() {
        return service.getAllReports()
                .stream()
                .map(KPIReportMapper::toResponseDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public KPIReportResponseDTO getById(@PathVariable Long id) {
        KPIReport report = service.getReportById(id);
        return KPIReportMapper.toResponseDTO(report);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteReport(id);
        return "Report with ID " + id + " deleted successfully.";
    }
}

