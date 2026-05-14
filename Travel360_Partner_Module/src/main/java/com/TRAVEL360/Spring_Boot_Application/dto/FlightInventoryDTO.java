package com.TRAVEL360.Spring_Boot_Application.dto;

import com.TRAVEL360.Spring_Boot_Application.entity.enums.FlightStatus;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightInventoryDTO {

    private Long partnerId;
    private String flightNumber;
    private String source;
    private String destination;
    private LocalDate travelDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private Integer totalSeats;
    private Integer availableSeats;
    private Double price;
    private FlightStatus status;
}
