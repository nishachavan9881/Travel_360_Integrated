package com.TRAVEL360.Spring_Boot_Application.dto;

import com.TRAVEL360.Spring_Boot_Application.entity.enums.PartnerStatus;
import com.TRAVEL360.Spring_Boot_Application.entity.enums.PartnerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartnerDTO {

    private String name;
    private PartnerType type;
    private String contactEmail;
    private String contactPhone;
    private PartnerStatus status;
}
