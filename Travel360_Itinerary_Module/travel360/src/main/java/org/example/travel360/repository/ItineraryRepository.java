package org.example.travel360.repository;

import org.example.travel360.model.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItineraryRepository extends JpaRepository<Itinerary, Integer> {

    //Fetch itineraries using userId
    List<Itinerary> findByUserId(Integer userId);

}