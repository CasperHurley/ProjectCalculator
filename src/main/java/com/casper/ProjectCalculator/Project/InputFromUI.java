package com.casper.ProjectCalculator.Project;

import java.util.ArrayList;
import java.util.List;

public class InputFromUI {
    String name;
    List<Double> listOfMeasurements;

    public InputFromUI() {

    }

    public InputFromUI(List<Double> listOfMeasurements) {
        this.listOfMeasurements = listOfMeasurements;
    }

    public InputFromUI(List<Double> listOfMeasurements, String name) {
        this.listOfMeasurements = listOfMeasurements;
        this.name = name;
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
}
