package com.airportsystem.service;

import com.airportsystem.models.Baggage;
import com.airportsystem.models.Cargo;
import com.airportsystem.models.FlightEntity;
import com.airportsystem.repository.FlightEntityRepository;
import com.airportsystem.response.AirportResponse;
import com.airportsystem.response.WeightResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class FlightEntityServiceImpl implements FlightEntityService {

    final FlightEntityRepository flightEntityRepository;

    @Override
    public WeightResponse getWeight(int number, Date date) {

        var cargo = flightEntityRepository.getFlightWeight(number, date).getCargoEntity();
        var baggageList = cargo.getBaggage();
        var cargoList = cargo.getCargo();
        int baggageSum = 0, cargoSum = 0, totalSum = 0;
        for (Baggage i : baggageList) {
            if (i.getWeightUnit().equals("lb")) {
                baggageSum += (i.getWeight() * 0.45359237);
            } else {
                baggageSum += i.getWeight();
            }
        }
        for (Cargo i : cargoList) {
            if (i.getWeightUnit().equals("lb")) {
                cargoSum += (i.getWeight() * 0.45359237);
            } else {
                cargoSum += i.getWeight();
            }
        }
        totalSum = cargoSum + baggageSum;
        return new WeightResponse(cargoSum, baggageSum, totalSum);
    }

    @Override
    public AirportResponse getAirportStatus(String IATA, Date date) {
        var departureList = flightEntityRepository.getDepartureAirport(IATA, date);
        var arrivalList = flightEntityRepository.getArrivalAirport(IATA, date);
        int numberOfBaggageDeparting = 0, numberOfBaggageArriving = 0;
        int numberOfFlightsDeparting = departureList.size();
        int numberOfFlightsArriving = arrivalList.size();
        if (numberOfFlightsDeparting == 0) {
            numberOfBaggageDeparting = 0;
        } else {
            for (FlightEntity i : departureList) {
                for (Baggage j : i.getCargoEntity().getBaggage()) {
                    numberOfBaggageDeparting += j.getPieces();
                }
            }
        }
        if (numberOfFlightsArriving == 0) {
            numberOfBaggageArriving = 0;
        } else {
            for (FlightEntity i : arrivalList) {
                for (Baggage j : i.getCargoEntity().getBaggage()) {
                    numberOfBaggageArriving += j.getPieces();
                }
            }
        }
        return new AirportResponse(numberOfFlightsDeparting, numberOfFlightsArriving, numberOfBaggageDeparting, numberOfBaggageArriving);
    }

    @Override
    public List<FlightEntity> save(List<FlightEntity> list) {
        return flightEntityRepository.saveAll(list);
    }


}
