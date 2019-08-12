package com.casper.ProjectCalculator.Project;

import com.casper.ProjectCalculator.Util.Conversion.Conversion;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class ProjectTest {

    Conversion conversion = new Conversion();

    @Test
    public void testProjectCreation() throws ExecutionException, InterruptedException {
        List<Double> listOfMeasurements = new ArrayList<>();
        listOfMeasurements.add(25.0);
        listOfMeasurements.add(35.25);
        listOfMeasurements.add(45.0);
        listOfMeasurements.add(45.0);
        listOfMeasurements.add(55.0);
        listOfMeasurements.add(25.75);
        listOfMeasurements.add(35.25);
        listOfMeasurements.add(45.25);
        listOfMeasurements.add(45.0);
        listOfMeasurements.add(55.0);
        List<Double> stockLengthsToCheck = new ArrayList<>();
//        stockLengthsToCheck.add(conversion.convertFeetToInches(8.0));
        stockLengthsToCheck.add(conversion.convertFeetToInches(10.0));
        stockLengthsToCheck.add(conversion.convertFeetToInches(12.0));
        InputFromUI inputFromUI = new InputFromUI("Test Project", listOfMeasurements, stockLengthsToCheck);
        Project testProject = new Project(inputFromUI);

        testProject.getBestCutPlansForDifferentStockLengths().forEach(cutPlan -> {
            System.out.println("New Cut Plan");
            cutPlan.getBoardList().forEach(board -> {
                System.out.println("New Board");
                board.getMeasurementsInBoard().forEach(measurement -> {
                    System.out.println(measurement);
                });
            });
        });
    }
}