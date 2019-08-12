package com.casper.ProjectCalculator.Project;

import com.casper.ProjectCalculator.Permutations.CheckAllPermutations;

import java.util.ArrayList;
import java.util.List;

public class Project {
    String name;
    List<Double> listOfMeasurements;
    List<CutPlan> bestCutPlansForDifferentStockLengths;

    public Project(InputFromUI inputFromUI) {
        this.name = inputFromUI.getName();
        this.listOfMeasurements = inputFromUI.getListOfMeasurements();
        this.bestCutPlansForDifferentStockLengths = calculateBestCutPlansForDifferentStockLengths(inputFromUI);
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

    public List<CutPlan> getBestCutPlansForDifferentStockLengths() {
        return bestCutPlansForDifferentStockLengths;
    }

    public void setBestCutPlansForDifferentStockLengths(List<CutPlan> bestCutPlansForDifferentStockLengths) {
        this.bestCutPlansForDifferentStockLengths = bestCutPlansForDifferentStockLengths;
    }

    public List<CutPlan> calculateBestCutPlansForDifferentStockLengths(InputFromUI inputFromUI) {
        List<CutPlan> bestCutPlansForDifferentStockLengths = new ArrayList<>();
        for (Double stockLengthToCheck : inputFromUI.getStockLengthsToCheck()) {
            CheckAllPermutations checkAllPermutations = new CheckAllPermutations();
            CutPlan bestCutPlanForCurrentStockLength = checkAllPermutations.getBestCutPlan();
            bestCutPlansForDifferentStockLengths.add(bestCutPlanForCurrentStockLength);
        }
        return bestCutPlansForDifferentStockLengths;
    }

}
