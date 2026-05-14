package com.TRAVEL360.Spring_Boot_Application.dto;

import com.TRAVEL360.Spring_Boot_Application.entity.enums.CabStatus;
import com.TRAVEL360.Spring_Boot_Application.entity.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CabInventoryDTO {

    private Long partnerId;
    private VehicleType vehicleType;
    private String carNumber;
    private String driverName;
    private String driverPhone;
    private String source;
    private String destination;
    private Integer availableUnits;
    private Double pricePerKm;
    private Double baseFare;
    private CabStatus status;
}
