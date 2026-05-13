package com.travel360.analyticsservice.service;

import com.travel360.analyticsservice.entity.KPIReport;
import com.travel360.analyticsservice.repository.KPIReportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KPIReportServiceTest {

    @Mock
    private KPIReportRepository repository;

    @InjectMocks
    private KPIReportServiceImpl service;

    // ✅ TEST 1: Create Report
    @Test
    void testCreateReport() {

        KPIReport report = new KPIReport();
        report.setScope("Travel");

        when(repository.save(any(KPIReport.class))).thenReturn(report);

        KPIReport result = service.createReport(report);

        assertNotNull(result);
        assertEquals("Travel", result.getScope());
    }

    // ✅ TEST 2: Get All Reports
    @Test
    void testGetAllReports() {

        when(repository.findAll()).thenReturn(List.of(new KPIReport()));

        List<KPIReport> reports = service.getAllReports();

        assertEquals(1, reports.size());
    }

    // ✅ TEST 3: Delete Report
    @Test
    void testDeleteReport() {

        Long id = 1L;

        when(repository.existsById(id)).thenReturn(true);

        service.deleteReport(id);

        verify(repository, times(1)).deleteById(id);
    }
}