package com.airportsystem.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "flight_entity")
public class FlightEntity {

    @Id
    @Column(name = "flight_id")
    private long flightId;
    private int flightNumber;
    @Column(name = "departure_airport_IATA_code")
    private String departureAirportIATACode;
    @Column(name = "arrival_airport_IATA_code")
    private String arrivalAirportIATACode;
   // @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssXXX")
   // @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date departureDate;
 //yyyy:MM:DDT
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "cargo_entity")
    @MapsId
    private CargoEntity cargoEntity;

    public FlightEntity() {}


}
