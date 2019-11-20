package com.casper.ProjectCalculator.Project;

import com.casper.ProjectCalculator.Util.Conversion.Conversion;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
        InputPayload inputPayload = new InputPayload("Test Project", listOfMeasurements, 12.0);
        Project testProject = new Project(inputPayload);
        System.out.println("New Cut Plan");
        testProject.getBestCutPlan().getBoardList().forEach(board -> {
            System.out.println("New Board");
            board.getMeasurementsInBoard().forEach(measurement -> {
                System.out.println(measurement);
            });
        });
    }
}