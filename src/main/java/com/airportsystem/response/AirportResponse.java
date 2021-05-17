package com.airportsystem.response;

import lombok.Data;

@Data
public class AirportResponse {
    private int numberOfFlightsDeparting;
    private int numberOfFlightsArriving;
    private int numberOfBaggageDeparting;
    private int numberOfBaggageArriving;

    public AirportResponse(int numberOfFlightsDeparting, int numberOfFlightsArriving, int numberOfBaggageDeparting, int numberOfBaggageArriving) {
        this.numberOfFlightsDeparting = numberOfFlightsDeparting;
        this.numberOfFlightsArriving = numberOfFlightsArriving;
        this.numberOfBaggageDeparting = numberOfBaggageDeparting;
        this.numberOfBaggageArriving = numberOfBaggageArriving;
    }
}
