package org.vibhav.app.compliance_service.compliance.entity;



import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "compliance_report")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplianceReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    private String scope;

    @Column(columnDefinition = "TEXT")
    private String metrics;

    private LocalDateTime generatedDate;
}
