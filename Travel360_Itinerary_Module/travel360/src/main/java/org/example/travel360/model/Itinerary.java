package org.example.travel360.model;

import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "itinerary")
@Access(AccessType.FIELD)
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itinerary_id")
    private Integer itineraryId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "status")
    private String status;

    // Getters
    public Integer getItineraryId() {
        return itineraryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }

    //Setters (needed because you SAVE itinerary from Java)

    public void setItineraryId(Integer itineraryId) {
        this.itineraryId = itineraryId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}