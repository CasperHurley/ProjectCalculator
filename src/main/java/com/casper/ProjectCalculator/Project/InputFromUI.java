package com.casper.ProjectCalculator.Project;

import java.util.ArrayList;
import java.util.List;

public class InputFromUI {
    String name;
    List<Double> listOfMeasurements;
    Double lengthOfStockBoardInInches;

    public InputFromUI() {
//        this.name = name;
//        this.listOfMeasurements = listOfMeasurements;
//        this.lengthOfStockBoardInInches = lengthOfStockBoardInInches;
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

    public Double getLengthOfStockBoardInInches() {
        return lengthOfStockBoardInInches;
    }

    public void setLengthOfStockBoardInInches(Double lengthOfStockBoardInInches) {
        this.lengthOfStockBoardInInches = lengthOfStockBoardInInches;
    }
}
