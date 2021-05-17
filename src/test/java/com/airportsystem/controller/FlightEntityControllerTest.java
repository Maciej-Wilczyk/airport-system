package com.airportsystem.controller;

import com.airportsystem.models.Baggage;
import com.airportsystem.models.Cargo;
import com.airportsystem.models.CargoEntity;
import com.airportsystem.models.FlightEntity;
import com.airportsystem.repository.CargoEntityRepository;
import com.airportsystem.repository.FlightEntityRepository;
import com.airportsystem.response.AirportResponse;
import com.airportsystem.response.WeightResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class FlightEntityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FlightEntityRepository flightEntityRepository;


    @BeforeEach
    public void init() throws ParseException {
        flightEntityRepository.save(prepareData());
    }

    @Test
    void should_get_weight() throws Exception {
        //given
        FlightEntity flightEntity = flightEntityRepository.findAll().get(flightEntityRepository.findAll().size() - 1);
        var date = flightEntity.getDepartureDate();
        var dateFormatted = parseDate(date);
        //when
        MvcResult mvcResult = mockMvc.perform(get("/weight/"+ flightEntity.getFlightNumber() + "/"+dateFormatted))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        //then
        WeightResponse weightResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), WeightResponse.class);
        assertThat(weightResponse).isNotNull();
        assertThat(weightResponse.getBaggageWeight()).isEqualTo(59);
        assertThat(weightResponse.getCargoWeight()).isEqualTo(156);
        assertThat(weightResponse.getTotalWeight()).isEqualTo(215);
        assertThat(weightResponse.getWeightUnit()).isEqualTo("kg");
    }


    @Test
    void should_get_airport_status() throws Exception {
        //given
        FlightEntity flightEntity = flightEntityRepository.findAll().get(flightEntityRepository.findAll().size() - 1);
        var date = flightEntity.getDepartureDate();
        var dateFormatted = parseDate(date);
        //when
        MvcResult mvcResult = mockMvc.perform(get("/airport/" + flightEntity.getDepartureAirportIATACode() + "/" + dateFormatted))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        //then
        AirportResponse airportResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), AirportResponse.class);
        assertThat(airportResponse).isNotNull();
        assertThat(airportResponse.getNumberOfFlightsDeparting()).isEqualTo(1);
        assertThat(airportResponse.getNumberOfFlightsArriving()).isEqualTo(0);
        assertThat(airportResponse.getNumberOfBaggageDeparting()).isEqualTo(15);
        assertThat(airportResponse.getNumberOfBaggageArriving()).isEqualTo(0);
    }

    private FlightEntity prepareData() throws ParseException {
        FlightEntity flightEntity = new FlightEntity();
        flightEntity.setFlightId(10L);
        flightEntity.setFlightNumber(1111);
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse("2021-05-17T00:45:52+02:00");
        flightEntity.setDepartureDate(date);
        flightEntity.setDepartureAirportIATACode("KRK");
        flightEntity.setArrivalAirportIATACode("GDN");
        CargoEntity cargoEntity = new CargoEntity();
        cargoEntity.setFlightId(10L);
        cargoEntity.setBaggage(List.of(new Baggage(10L, 50, "kg", 10), new Baggage(1L, 20, "lb", 5)));
        cargoEntity.setCargo(List.of(new Cargo(10L, 100, "kg", 2), new Cargo(1L, 125, "lb", 10)));
        flightEntity.setCargoEntity(cargoEntity);
        return flightEntity;
    }

    private String parseDate(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date.toString());
        SimpleDateFormat print = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        return print.format(parsedDate);
    }
}