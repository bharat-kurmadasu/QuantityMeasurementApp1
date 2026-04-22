package com.quantit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testConversion_FeetToInches() {
        assertEquals(12.0, QuantityMeasurementApp.convert(1.0, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCH));
    }

    @Test
    void testConversion_InchesToFeet() {
        assertEquals(2.0, QuantityMeasurementApp.convert(24.0, QuantityMeasurementApp.LengthUnit.INCH, QuantityMeasurementApp.LengthUnit.FEET));
    }

    @Test
    void testConversion_YardsToInches() {
        assertEquals(36.0, QuantityMeasurementApp.convert(1.0, QuantityMeasurementApp.LengthUnit.YARD, QuantityMeasurementApp.LengthUnit.INCH));
    }

    @Test
    void testConversion_InchesToYards() {
        assertEquals(2.0, QuantityMeasurementApp.convert(72.0, QuantityMeasurementApp.LengthUnit.INCH, QuantityMeasurementApp.LengthUnit.YARD));
    }

    @Test
    void testConversion_CentimetersToInches() {
        double result = QuantityMeasurementApp.convert(2.54, QuantityMeasurementApp.LengthUnit.CENTIMETER, QuantityMeasurementApp.LengthUnit.INCH);
        assertEquals(1.0, result, 1e-6);
    }

    @Test
    void testConversion_FeetToYard() {
        assertEquals(2.0, QuantityMeasurementApp.convert(6.0, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.YARD));
    }

    @Test
    void testConversion_ZeroValue() {
        assertEquals(0.0, QuantityMeasurementApp.convert(0.0, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCH));
    }

    @Test
    void testConversion_NegativeValue() {
        assertEquals(-12.0, QuantityMeasurementApp.convert(-1.0, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCH));
    }

    @Test
    void testConversion_InvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class, () -> QuantityMeasurementApp.convert(1.0, null, QuantityMeasurementApp.LengthUnit.INCH));
    }

    @Test
    void testConversion_NaNOrInfinite_Throws() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityMeasurementApp.QuantityLength(Double.NaN, QuantityMeasurementApp.LengthUnit.FEET));
        assertThrows(IllegalArgumentException.class, () -> new QuantityMeasurementApp.QuantityLength(Double.POSITIVE_INFINITY, QuantityMeasurementApp.LengthUnit.FEET));
    }
}
