package com.casper.ProjectCalculator.Project;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class LumberPlanTest {

    @Ignore
    @Test
    public void determineGoalNumberOfBoards() {
        List<Double> listOfMeasurements = new ArrayList<>();
        listOfMeasurements.add(25.0);
        listOfMeasurements.add(35.0);
        listOfMeasurements.add(45.0);
        listOfMeasurements.add(45.0);
        listOfMeasurements.add(55.0);
        Double lengthOfStockBoardInInches = 120.0;
//        CutPlan cutPlan = new CutPlan(listOfMeasurements, lengthOfStockBoardInInches);
//        assertThat(cutPlan.determineGoalNumberOfBoards(), is(1.70));
    }
}