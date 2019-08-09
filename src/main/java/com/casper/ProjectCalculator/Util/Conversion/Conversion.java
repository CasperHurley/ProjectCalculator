package com.casper.ProjectCalculator.Util.Conversion;

public class Conversion {
    public double convertFeetToInches(double feet) {
        double inches = feet * 12;
        return inches;
    }

    public double convertFeetToInches(int feet) {
        double inches = feet * 12;
        return inches;
    }

    public double convertInchesToFeet(int inches) {
        double feet = inches / 12;
        return feet;
    }

    public double convertInchesToFeet(double inches) {
        double feet = inches / 12;
        return feet;
    }

    public double convertFeetAndInchesToJustInches(int feet, int inches) {
        double totalInches = feet * 12;
        totalInches += inches;
        return totalInches;
    }

    public double convertFeetAndInchesToJustInches(double feet, double inches) {
        double totalInches = feet * 12;
        totalInches += inches;
        return totalInches;
    }

    public double convertFeetAndInchesToJustInches(double feet, int inches) {
        double totalInches = feet / 12;
        totalInches += inches;
        return totalInches;
    }

    public double convertFeetAndInchesToJustInches(int feet, double inches) {
        double totalInches = feet / 12;
        totalInches += inches;
        return totalInches;
    }
}
