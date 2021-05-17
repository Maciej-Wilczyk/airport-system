package com.airportsystem.service;

import com.airportsystem.response.AirportResponse;
import com.airportsystem.response.WeightResponse;
import java.util.Date;

public interface FlightEntityService {

    WeightResponse getWeight(int number, Date date);

    AirportResponse getAirportStatus(String IATA, Date date);

}
