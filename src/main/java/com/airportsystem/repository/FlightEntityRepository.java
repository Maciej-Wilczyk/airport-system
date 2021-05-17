package com.airportsystem.repository;

import com.airportsystem.models.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface FlightEntityRepository extends JpaRepository<FlightEntity,Long> {

    @Query(value = "SELECT * FROM  flight_entity WHERE flight_number=:flight_number AND departure_date=:departure_date", nativeQuery = true)
    FlightEntity getFlightWeight(
            @Param("flight_number") int flight_number,
            @Param("departure_date") Date departure_date
            );

    @Query(value = "SELECT * FROM  flight_entity WHERE departure_airport_IATA_code=:IATA AND departure_date=:departure_date", nativeQuery = true)
    List<FlightEntity> getDepartureAirport(
                    @Param("IATA") String IATA,
                    @Param("departure_date") Date departure_date
            );

    @Query(value = "SELECT * FROM  flight_entity WHERE arrival_airport_IATA_code=:IATA AND departure_date=:departure_date", nativeQuery = true)
    List<FlightEntity> getArrivalAirport(
            @Param("IATA") String IATA,
            @Param("departure_date") Date departure_date
    );


}
