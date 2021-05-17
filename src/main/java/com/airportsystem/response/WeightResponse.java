package com.airportsystem.response;

import lombok.Data;

@Data
public class WeightResponse {
    private int cargoWeight;
    private int baggageWeight;
    private int totalWeight;
    private String weightUnit;

    public WeightResponse(int cargoWeight, int baggageWeight, int totalWeight) {
        this.cargoWeight = cargoWeight;
        this.baggageWeight = baggageWeight;
        this.totalWeight = totalWeight;
        this.weightUnit = "kg";
    }

}
