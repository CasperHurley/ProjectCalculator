package com.casper.ProjectCalculator.Calculator;

import com.casper.ProjectCalculator.Util.Conversion.Conversion;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void getBestCutPlan() {
    }

    @Test
    public void setBestCutPlan() {
    }

    @Test
    public void getSumOfAllMeasurements() throws ExecutionException, InterruptedException {
        Conversion conversion = new Conversion();
        List<Double> listOfMeasurements = new ArrayList<>();
        listOfMeasurements.add(25.0);
        listOfMeasurements.add(25.0);
        listOfMeasurements.add(55.0);
        listOfMeasurements.add(55.0);
        Double lengthOfStockBoardInInches = conversion.convertFeetToInches(12.0);
        Calculator calculator = new Calculator(listOfMeasurements, lengthOfStockBoardInInches);
        Double expectedTotal = 160.00;
        assertEquals(expectedTotal, calculator.getSumOfAllMeasurements());
    }

    @Test
    public void determineIfCurrentCutPlanIsBetterThanCurrentBestCutPlan() {
    }

    @Test
    public void tellMeIfCurrentCutPlanIsBetterThanCurrentBestCutPlan() {
    }

    @Test
    public void determineBestCutPlan() {
    }

    @Test
    public void createPermutationThreads() {
    }

    @Test
    public void shutdownExecutor() {
    }

    @Test
    public void getAllStartingPermutations() throws ExecutionException, InterruptedException {
        Conversion conversion = new Conversion();
        List<Double> listOfMeasurements = new ArrayList<>();
        listOfMeasurements.add(25.0);
        listOfMeasurements.add(55.0);
        listOfMeasurements.add(35.0);
        listOfMeasurements.add(55.0);
        Double lengthOfStockBoardInInches = conversion.convertFeetToInches(12.0);
        Calculator calculator = new Calculator(listOfMeasurements, lengthOfStockBoardInInches);
        List<List<Double>> expectedStartingPermutations = new ArrayList<>();
        expectedStartingPermutations.add(calculator.getNextPermutation(listOfMeasurements));
        expectedStartingPermutations.add(calculator.getNextPermutation(listOfMeasurements));
        expectedStartingPermutations.add(calculator.getNextPermutation(listOfMeasurements));
        expectedStartingPermutations.add(calculator.getNextPermutation(listOfMeasurements));
        assertEquals(expectedStartingPermutations, calculator.getAllStartingPermutations(listOfMeasurements));
    }

    @Test
    public void getNextPermutation() throws ExecutionException, InterruptedException {
        Conversion conversion = new Conversion();
        List<Double> listOfMeasurements = new ArrayList<>();
        listOfMeasurements.add(25.0);
        listOfMeasurements.add(25.0);
        listOfMeasurements.add(55.0);
        listOfMeasurements.add(35.0);
        listOfMeasurements.add(55.0);
        Double lengthOfStockBoardInInches = conversion.convertFeetToInches(12.0);
        Calculator calculator = new Calculator(listOfMeasurements, lengthOfStockBoardInInches);
        List<Double> firstTimeThrough = new ArrayList<>();
        firstTimeThrough.add(25.0);
        firstTimeThrough.add(55.0);
        firstTimeThrough.add(35.0);
        firstTimeThrough.add(55.0);
        firstTimeThrough.add(25.0);
        assertEquals(firstTimeThrough, calculator.getNextPermutation(listOfMeasurements));
        List<Double> secondTimeThrough = new ArrayList<>();
        secondTimeThrough.add(55.0);
        secondTimeThrough.add(35.0);
        secondTimeThrough.add(55.0);
        secondTimeThrough.add(25.0);
        secondTimeThrough.add(25.0);
        assertEquals(secondTimeThrough, calculator.getNextPermutation(firstTimeThrough));
    }

    @Test
    public void checkEachPermutationInThreadForBestCutPlan() {
    }
}