package com.casper.ProjectCalculator.Project;

import java.util.ArrayList;
import java.util.List;

public class InputFromUI {
    String name;
    List<Double> listOfMeasurements;
    List<Double> stockLengthsToCheck;

    public InputFromUI() {
    }

    public InputFromUI(String name, List<Double> listOfMeasurements, List<Double> stockLengthsToCheck) {
        this.name = name;
        this.listOfMeasurements = listOfMeasurements;
        this.stockLengthsToCheck = stockLengthsToCheck;
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

    public List<Double> getStockLengthsToCheck() {
        return stockLengthsToCheck;
    }

    public void setStockLengthsToCheck(List<Double> stockLengthsToCheck) {
        this.stockLengthsToCheck = stockLengthsToCheck;
    }
}
