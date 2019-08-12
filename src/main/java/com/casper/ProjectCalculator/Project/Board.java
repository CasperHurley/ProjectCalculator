package com.casper.ProjectCalculator.Project;

import com.casper.ProjectCalculator.Util.CommonUtil;

import java.util.List;

public class Board {
    Double length;
    Double width;
    Double thickness;
    Double lengthRemaining;
    List<Double> measurementsInBoard;
    boolean boardIsFull = false;

    public Board(Double length) {
        this.length = length;
        this.lengthRemaining = length;

        // make sure board is good even with both ends squared with a saw
        // square one end
        CommonUtil.removeWidthOfSawBlade(this.lengthRemaining);
        // square the other end
        CommonUtil.removeWidthOfSawBlade(this.lengthRemaining);
    }

    public Board(Double length, Double width, Double thickness) {
        this.length = length;
        this.width = width;
        this.thickness = thickness;
    }

    public void addMeasurementToBoard(Double measurement) {
        if (checkIfBoardCanFitMeasurement(measurement)) {
            this.measurementsInBoard.add(measurement);
        } else {
            this.boardIsFull = true;
        }
    }

    public boolean checkIfBoardCanFitMeasurement(Double measurement) {
        return measurement <= this.lengthRemaining;
    }
}
