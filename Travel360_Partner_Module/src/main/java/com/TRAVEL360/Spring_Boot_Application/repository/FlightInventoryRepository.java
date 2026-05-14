package com.TRAVEL360.Spring_Boot_Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TRAVEL360.Spring_Boot_Application.entity.FlightInventory;

public interface FlightInventoryRepository extends JpaRepository<FlightInventory, Long> {
}