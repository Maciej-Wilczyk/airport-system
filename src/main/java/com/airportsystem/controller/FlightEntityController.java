package com.airportsystem.controller;

import com.airportsystem.models.FlightEntity;
import com.airportsystem.repository.FlightEntityRepository;
import com.airportsystem.service.FlightEntityService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequiredArgsConstructor
public class FlightEntityController {

    final FlightEntityRepository flightEntityRepository;

    final FlightEntityService flightEntityService;

    @GetMapping("/weight/{flNum}/{date}")
    public Object weight(@PathVariable(value = "flNum") int flNum, @PathVariable(value = "date") @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX") String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        try {
            Date date1 = df.parse(date);
            System.out.println(date);
            return flightEntityService.getWeight(flNum, date1);
        } catch (ParseException e) {
            return "wrong date format";
        } catch (NullPointerException e) {
            return "[]";
        }
    }

    @GetMapping("/airport/{IATA}/{date}")
    public Object airport(@PathVariable(value = "IATA") String IATA, @PathVariable(value = "date") @JsonFormat(pattern = "YYYY-MM-ddThh:mm:ssXXX") String date) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        try {
            Date date1 = df.parse(date);
            return flightEntityService.getAirportStatus(IATA, date1);
        } catch (ParseException e) {
            return "wrong date format";
        } catch (NullPointerException e) {
            return "[]";
        }


    }
}
