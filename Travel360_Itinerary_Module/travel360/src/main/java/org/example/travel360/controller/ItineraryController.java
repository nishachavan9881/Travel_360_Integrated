package org.example.travel360.controller;

import org.example.travel360.service.ItineraryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ItineraryController implements CommandLineRunner {

    private final ItineraryService itineraryService;

    public ItineraryController(ItineraryService itineraryService) {
        this.itineraryService = itineraryService;
    }

    @Override
    public void run(String... args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("     Travel360 Itinerary Module  ");
        System.out.println("=================================");

        System.out.print("Enter User ID: ");
        Integer userId = scanner.nextInt();

        itineraryService.generateItinerary(userId);

        System.out.println("=================================");
    }
}
