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
    boolean measurementTooLongForStockLength = false;

    public Board(Double length) {
        this.length = length;
        this.lengthRemaining = length;
        // make sure board is good even with both ends squared with a saw
        CommonUtil.removeWidthOfSawBlade(this.lengthRemaining); // square one end
        CommonUtil.removeWidthOfSawBlade(this.lengthRemaining); // square the other end
    }

    public Board(Double length, Double width, Double thickness) {
        this.length = length;
        this.width = width;
        this.thickness = thickness;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getThickness() {
        return thickness;
    }

    public void setThickness(Double thickness) {
        this.thickness = thickness;
    }

    public Double getLengthRemaining() {
        return lengthRemaining;
    }

    public void setLengthRemaining(Double lengthRemaining) {
        this.lengthRemaining = lengthRemaining;
    }

    public List<Double> getMeasurementsInBoard() {
        return measurementsInBoard;
    }

    public void setMeasurementsInBoard(List<Double> measurementsInBoard) {
        this.measurementsInBoard = measurementsInBoard;
    }

    public boolean isBoardIsFull() {
        return boardIsFull;
    }

    public void setBoardIsFull(boolean boardIsFull) {
        this.boardIsFull = boardIsFull;
    }

    public boolean isMeasurementTooLongForStockLength() {
        return measurementTooLongForStockLength;
    }

    public void setMeasurementTooLongForStockLength(boolean measurementTooLongForStockLength) {
        this.measurementTooLongForStockLength = measurementTooLongForStockLength;
    }

    public void tryToAddMeasurementToBoard(Double measurement) {
        if (checkIfBoardCanFitMeasurement(measurement)) {
            this.measurementsInBoard.add(measurement);
        } else {
            this.boardIsFull = true;
        }
    }

    public boolean checkIfBoardCanFitMeasurement(Double measurement) {
        boolean boardCanFitMeasurement = measurement <= this.lengthRemaining;
        if (measurement > this.length) {
            this.measurementTooLongForStockLength = true;
        }
        return boardCanFitMeasurement;
    }
}
