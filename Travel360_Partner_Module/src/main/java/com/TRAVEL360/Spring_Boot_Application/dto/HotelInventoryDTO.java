package com.TRAVEL360.Spring_Boot_Application.dto;

import com.TRAVEL360.Spring_Boot_Application.entity.enums.HotelStatus;
import com.TRAVEL360.Spring_Boot_Application.entity.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelInventoryDTO {

    private Long partnerId;
    private String hotelName;
    private String location;
    private RoomType roomType;
    private Integer totalRooms;
    private Integer availableRooms;
    private Double pricePerNight;
    private HotelStatus status;
}
