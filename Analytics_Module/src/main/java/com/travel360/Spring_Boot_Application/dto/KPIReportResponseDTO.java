package com.travel360.Spring_Boot_Application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KPIReportResponseDTO {

    private Long reportId;
    private String scope;
    private String metrics;
    private LocalDateTime generatedDate;
}
