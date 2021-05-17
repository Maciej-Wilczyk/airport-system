package com.airportsystem.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "cargo_entity")
public class CargoEntity {

    @Id
    @Column(name = "flight_id")
    private long flightId;

    @ElementCollection
    private List<Baggage> baggage;
    @ElementCollection
    private List<Cargo> cargo;

//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "cargoEntity")
//    private FlightEntity flightEntity;

    public CargoEntity() {}
}
