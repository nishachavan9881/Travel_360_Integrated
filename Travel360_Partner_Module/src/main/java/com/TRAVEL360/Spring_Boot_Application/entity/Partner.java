package com.TRAVEL360.Spring_Boot_Application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.TRAVEL360.Spring_Boot_Application.entity.enums.PartnerStatus;
import com.TRAVEL360.Spring_Boot_Application.entity.enums.PartnerType;

@Entity
@Table(name = "partners")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PartnerID")
    private Long partnerId;

    @Column(name = "Name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "Type")
    private PartnerType type;

    @Column(name = "ContactEmail")
    private String contactEmail;

    @Column(name = "ContactPhone")
    private String contactPhone;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private PartnerStatus status;
}
