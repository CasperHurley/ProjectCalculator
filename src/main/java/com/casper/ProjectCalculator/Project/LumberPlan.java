package com.casper.ProjectCalculator.Project;

import com.casper.ProjectCalculator.Permutations.CheckAllPermutations;

import java.util.List;
import java.util.concurrent.*;

public class LumberPlan {
    List<Double> listOfMeasurements;
    Double lengthOfStockBoardInInches;
    CutPlan cutPlan;

    public LumberPlan(List<Double> listOfMeasurements, Double lengthOfStockBoardInInches) {
        this.listOfMeasurements = listOfMeasurements;
        this.lengthOfStockBoardInInches = lengthOfStockBoardInInches;
        this.cutPlan = new CutPlan(listOfMeasurements, lengthOfStockBoardInInches, determineGoalNumberOfBoards());
    }

    public Double determineGoalNumberOfBoards() {
        Double totalLengthOfWoodNeededInInches = 0.0;
        for (Double measurement : this.listOfMeasurements) {
            totalLengthOfWoodNeededInInches += measurement;
        }
        return totalLengthOfWoodNeededInInches / this.lengthOfStockBoardInInches;
    }

    public void getBestCutPlanFromListOfMeasurements() {
        List<Callable<CutPlan>> permutationThreads = CheckAllPermutations.createPermutationThreads(this.listOfMeasurements, this.lengthOfStockBoardInInches, this.determineGoalNumberOfBoards());

    }
}
