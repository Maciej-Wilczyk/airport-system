package com.airportsystem.service;


import com.airportsystem.models.Baggage;
import com.airportsystem.models.Cargo;
import com.airportsystem.models.CargoEntity;
import com.airportsystem.models.FlightEntity;
import com.airportsystem.repository.FlightEntityRepository;
import com.airportsystem.response.AirportResponse;
import com.airportsystem.response.WeightResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class FlightEntityServiceImplTest {

    @Mock
    FlightEntityRepository flightEntityRepository;

    @InjectMocks
    FlightEntityServiceImpl flightEntityService;


    @Test
    public void should_get_weight() {
        //given
        given(flightEntityRepository.getFlightWeight(anyInt(), any())).willReturn(prepareData());
        WeightResponse expectedResponse = new WeightResponse(156, 59, 215);
        //when
        WeightResponse weightResponse = flightEntityService.getWeight(0, new Date());
        //then
        assertEquals(expectedResponse, weightResponse);

    }

    @Test
    public void should_get_airport_status() {
        //given
        given(flightEntityRepository.getDepartureAirport(anyString(), any())).willReturn(List.of(prepareData(), prepareData(), prepareData()));
        given(flightEntityRepository.getArrivalAirport(anyString(), any())).willReturn(List.of(prepareData()));
        AirportResponse expectedResponse = new AirportResponse(3, 1, 45, 15);
        //when
        AirportResponse airportResponse = flightEntityService.getAirportStatus("", new Date());
        //then
        assertEquals(airportResponse, expectedResponse);

    }

    private FlightEntity prepareData() {
        FlightEntity flightEntity = new FlightEntity();
        flightEntity.setFlightId(0);
        flightEntity.setFlightNumber(0);
        flightEntity.setDepartureDate(new Date());
        flightEntity.setDepartureAirportIATACode("KRK");
        flightEntity.setArrivalAirportIATACode("GDN");
        CargoEntity cargoEntity = new CargoEntity();
        cargoEntity.setFlightId(0);
        cargoEntity.setBaggage(List.of(new Baggage(0L, 50, "kg", 10), new Baggage(1L, 20, "lb", 5)));
        cargoEntity.setCargo(List.of(new Cargo(0L, 100, "kg", 2), new Cargo(1L, 125, "lb", 10)));
        flightEntity.setCargoEntity(cargoEntity);
        return flightEntity;
    }


}