package com.casper.ProjectCalculator.Project;

import java.util.ArrayList;
import java.util.List;

public class InputPayload {
    String name;
    List<Double> listOfMeasurements;
    Double stockLengthToCheck;

    public InputPayload() {
    }

    public InputPayload(String name, List<Double> listOfMeasurements, Double stockLengthsToCheck) {
        this.name = name;
        this.listOfMeasurements = listOfMeasurements;
        this.stockLengthToCheck = stockLengthsToCheck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getListOfMeasurements() {
        return listOfMeasurements;
    }

    public void setListOfMeasurements(List<Double> listOfMeasurements) {
        this.listOfMeasurements = listOfMeasurements;
    }

    public Double getStockLengthToCheck() {
        return stockLengthToCheck;
    }

    public void setStockLengthToCheck(Double stockLengthsToCheck) {
        this.stockLengthToCheck = stockLengthsToCheck;
    }
}
