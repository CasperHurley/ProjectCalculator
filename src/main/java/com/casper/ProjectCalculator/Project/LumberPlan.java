package com.casper.ProjectCalculator.Project;

import java.util.List;

public class LumberPlan {
    List<Double> listOfMeasurements;
    CutPlan cutPlan;

    public LumberPlan(List<Double> listOfMeasurements) {
        this.listOfMeasurements = listOfMeasurements;
        this.cutPlan = new CutPlan();
    }
}
