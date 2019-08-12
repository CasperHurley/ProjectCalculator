package com.casper.ProjectCalculator.Project;

import com.casper.ProjectCalculator.Util.Conversion.Conversion;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CutPlanTest {

    Conversion conversion = new Conversion();

    @Test
    public void testCutPlanCreation() {
        List<Double> listOfMeasurements = new ArrayList<>();
        listOfMeasurements.add(25.0);
        listOfMeasurements.add(35.0);
        listOfMeasurements.add(45.0);
        listOfMeasurements.add(45.0);
        listOfMeasurements.add(55.0);
        listOfMeasurements.add(25.0);
        listOfMeasurements.add(35.0);
        listOfMeasurements.add(45.0);
        listOfMeasurements.add(45.0);
        listOfMeasurements.add(55.0);
        listOfMeasurements.add(25.0);
        listOfMeasurements.add(35.0);
        listOfMeasurements.add(45.0);
        listOfMeasurements.add(45.0);
        listOfMeasurements.add(55.0);
        Double lengthOfStockBoardInInches = conversion.convertFeetToInches(12.0);
        CutPlan testCutPlan = new CutPlan(listOfMeasurements, lengthOfStockBoardInInches);
        testCutPlan.getBoardList().forEach(board -> {
            System.out.println("Start of board");
            for (Double measurement: board.getMeasurementsInBoard()) {
                System.out.println(measurement);
            }
            System.out.println("");
        });
    }
}