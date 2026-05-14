package com.travel360.Spring_Boot_Application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KPIReportDTO {

    @NotBlank(message = "Scope cannot be empty")
    private String scope;

    @NotBlank(message = "Metrics cannot be empty")
    private String metrics;
}
