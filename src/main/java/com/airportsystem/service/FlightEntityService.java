package com.airportsystem.service;

import com.airportsystem.models.FlightEntity;
import com.airportsystem.response.AirportResponse;
import com.airportsystem.response.WeightResponse;
import java.util.Date;
import java.util.List;

public interface FlightEntityService {

    WeightResponse getWeight(int number, Date date);

    AirportResponse getAirportStatus(String IATA, Date date);

    List<FlightEntity> save(List<FlightEntity> list);
}
