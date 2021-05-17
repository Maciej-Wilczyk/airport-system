package com.airportsystem.models;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Cargo {

    private long id;
    private int weight;
    private String weightUnit;
    private int pieces;

    public Cargo() {}

    public Cargo(long id, int weight, String weightUnit, int pieces) {
        this.id = id;
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.pieces = pieces;
    }
}
