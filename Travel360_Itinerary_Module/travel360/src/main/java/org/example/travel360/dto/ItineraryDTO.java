package org.example.travel360.dto;

import java.time.LocalDate;


public class ItineraryDTO {

    private Integer itineraryId;
    private Integer userId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    // Constructors

    public ItineraryDTO(Integer itineraryId, Integer userId, LocalDate startDate, 
                        LocalDate endDate, String status) {
        this.itineraryId = itineraryId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    // Getters and Setters
    public Integer getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(Integer itineraryId) {
        this.itineraryId = itineraryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ItineraryDTO{" +
                "itineraryId=" + itineraryId +
                ", userId=" + userId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                '}';
    }
}

