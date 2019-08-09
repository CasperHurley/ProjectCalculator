package com.casper.ProjectCalculator.Util.Conversion;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConversionTest {
    Conversion conversion = new Conversion();

    @Test
    public void convertFeetToInches() {
        assertEquals(24, conversion.convertFeetToInches(2.0), 0);
    }

    @Test
    public void convertFeetToInches1() {
        assertEquals(24, conversion.convertFeetToInches(2), 0);

    }

    @Test
    public void convertInchesToFeet() {
    }

    @Test
    public void convertInchesToFeet1() {
    }

    @Test
    public void convertFeetAndInchesToJustInches() {
    }

    @Test
    public void convertFeetAndInchesToJustInches1() {
    }

    @Test
    public void convertFeetAndInchesToJustInches2() {
    }

    @Test
    public void convertFeetAndInchesToJustInches3() {
    }
}