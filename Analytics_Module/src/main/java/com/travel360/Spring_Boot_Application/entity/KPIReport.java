package com.travel360.Spring_Boot_Application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "kpi_reports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KPIReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    private String scope;

    private String metrics;

    private LocalDateTime generatedDate;

}

