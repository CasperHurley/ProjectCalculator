package com.casper.ProjectCalculator.Project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CutPlan {
    List<Board> boardList = new ArrayList<>();
    BigDecimal totalCost;
    Double lengthOfStockBoardInInches;
    Double goalNumberOfBoards;
    List<Double> currentPermutationOfListOfMeasurements;
    boolean stockLengthIsTooShort = false;

    public CutPlan(List<Double> currentPermutationOfListOfMeasurements, Double lengthOfStockBoardInInches, Double goalNumberOfBoards) {
        this.currentPermutationOfListOfMeasurements = currentPermutationOfListOfMeasurements;
        this.lengthOfStockBoardInInches = lengthOfStockBoardInInches;
        this.goalNumberOfBoards = goalNumberOfBoards;
        addNewBoardToList();
        determineTheBoardsNeededForTheCurrentPermutation();
    }

    public List<Board> getBoardList() {
        return boardList;
    }

    public void setBoardList(List<Board> boardList) {
        this.boardList = boardList;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public Double getLengthOfStockBoardInInches() {
        return lengthOfStockBoardInInches;
    }

    public void setLengthOfStockBoardInInches(Double lengthOfStockBoardInInches) {
        this.lengthOfStockBoardInInches = lengthOfStockBoardInInches;
    }

    public void determineTheBoardsNeededForTheCurrentPermutation() {
        for (Double measurement : currentPermutationOfListOfMeasurements) {
            tryToAddMeasurementToCurrentBoard(measurement);
            if (this.boardList.get(this.boardList.size() - 1).isMeasurementTooLongForStockLength()) {
                this.stockLengthIsTooShort = true;
                break;
            }
            else if (this.boardList.get(this.boardList.size() - 1).boardIsFull) {
                addNewBoardToList();
                tryToAddMeasurementToCurrentBoard(measurement);
            }
        }
    }

    public void tryToAddMeasurementToCurrentBoard(Double measurement) {
        this.boardList.get(this.boardList.size() - 1).tryToAddMeasurementToBoard(measurement);
    }

    public void addNewBoardToList() {
        this.boardList.add(new Board(this.lengthOfStockBoardInInches));
    }
}
