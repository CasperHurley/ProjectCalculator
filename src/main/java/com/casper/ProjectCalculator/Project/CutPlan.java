package com.casper.ProjectCalculator.Project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CutPlan {
    List<Board> boardList = new ArrayList<>();
    BigDecimal totalCost;
    Double lengthOfStockBoardInInches;

    public CutPlan(List<Double> currentPermutationOfListOfMeasurements, Double lengthOfStockBoardInInches, Double goalNumberOfBoards) {
        this.lengthOfStockBoardInInches = lengthOfStockBoardInInches;
    }

    public void determineTheBoardsNeededForTheCurrentPermutation() {

    }

    public void addBoardToList(Board board) {
        this.boardList.add(board);
    }
}
