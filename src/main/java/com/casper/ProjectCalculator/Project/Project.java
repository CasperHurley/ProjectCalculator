package com.casper.ProjectCalculator.Project;

import com.casper.ProjectCalculator.Calculator.Calculator;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Project {
    String name;
    List<Double> listOfMeasurements;
    CutPlan bestCutPlan;

    public Project(InputPayload inputPayload) throws ExecutionException, InterruptedException {
        this.name = inputPayload.getName();
        this.listOfMeasurements = inputPayload.getListOfMeasurements();
        this.bestCutPlan = calculateBestCutPlan(inputPayload.getStockLengthToCheck());
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

    public CutPlan getBestCutPlan() {
        return bestCutPlan;
    }

    public CutPlan calculateBestCutPlan(Double stockLengthToCheck) throws ExecutionException, InterruptedException {
        Calculator calculator = new Calculator(listOfMeasurements, stockLengthToCheck);
        CutPlan bestCutPlan = calculator.getBestCutPlan();
        return bestCutPlan;
    }

}
