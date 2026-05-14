package org.example.travel360.dto;

import java.time.LocalDate;


public class ReservationMinimalDTO {

    private LocalDate startDate;
    private LocalDate endDate;


    // Constructors

    public ReservationMinimalDTO(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters
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

    @Override
    public String toString() {
        return "ReservationMinimalDTO{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}


