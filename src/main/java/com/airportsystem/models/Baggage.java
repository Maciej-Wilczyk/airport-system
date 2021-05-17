package com.airportsystem.models;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Baggage {

    private long id;
    private int weight;
    private String weightUnit;
    private int pieces;

    public Baggage() { }

    public Baggage(long id, int weight, String weightUnit, int pieces) {
        this.id = id;
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.pieces = pieces;
    }
}
