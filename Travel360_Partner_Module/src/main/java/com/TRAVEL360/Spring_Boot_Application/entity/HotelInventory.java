package com.TRAVEL360.Spring_Boot_Application.entity;

import jakarta.persistence.*;
import com.TRAVEL360.Spring_Boot_Application.entity.enums.HotelStatus;
import com.TRAVEL360.Spring_Boot_Application.entity.enums.RoomType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "hotelinventory")
public class HotelInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Long hotelId;

    @Column(name = "partner_id", nullable = false)
    private Long partnerId;

    @Column(name = "hotel_name")
    private String hotelName;

    @Column(name = "location")
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type")
    private RoomType roomType;

    @Column(name = "total_rooms")
    private Integer totalRooms;

    @Column(name = "available_rooms")
    private Integer availableRooms;

    @Column(name = "price_per_night")
    private Double pricePerNight;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private HotelStatus status;

    // ===== Getters & Setters =====

}
