package com.quantit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ==================== UC8: LengthUnit Enum Tests ====================

    @Test
    void testLengthUnitEnum_FeetConstant() {
        assertEquals(1.0, LengthUnit.FEET.getConversionFactor());
    }

    @Test
    void testLengthUnitEnum_InchesConstant() {
        assertEquals(1.0 / 12.0, LengthUnit.INCH.getConversionFactor(), 1e-6);
    }

    @Test
    void testLengthUnitEnum_YardsConstant() {
        assertEquals(3.0, LengthUnit.YARD.getConversionFactor());
    }

    @Test
    void testLengthUnitEnum_CentimetersConstant() {
        assertEquals(0.0328084, LengthUnit.CENTIMETER.getConversionFactor(), 1e-6);
    }

    // ==================== UC8: LengthUnit ConvertToBaseUnit Tests ====================

    @Test
    void testConvertToBaseUnit_FeetToFeet() {
        assertEquals(5.0, LengthUnit.FEET.convertToBaseUnit(5.0));
    }

    @Test
    void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0, LengthUnit.INCH.convertToBaseUnit(12.0));
    }

    @Test
    void testConvertToBaseUnit_YardsToFeet() {
        assertEquals(3.0, LengthUnit.YARD.convertToBaseUnit(1.0));
    }

    @Test
    void testConvertToBaseUnit_CentimetersToFeet() {
        assertEquals(1.0, LengthUnit.CENTIMETER.convertToBaseUnit(30.48), 1e-3);
    }

    // ==================== UC8: LengthUnit ConvertFromBaseUnit Tests ====================

    @Test
    void testConvertFromBaseUnit_FeetToFeet() {
        assertEquals(2.0, LengthUnit.FEET.convertFromBaseUnit(2.0));
    }

    @Test
    void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0, LengthUnit.INCH.convertFromBaseUnit(1.0));
    }

    @Test
    void testConvertFromBaseUnit_FeetToYards() {
        assertEquals(1.0, LengthUnit.YARD.convertFromBaseUnit(3.0));
    }

    @Test
    void testConvertFromBaseUnit_FeetToCentimeters() {
        assertEquals(30.48, LengthUnit.CENTIMETER.convertFromBaseUnit(1.0), 1e-3);
    }

    // ==================== UC8: Backward Compatibility Tests (UC1-UC7) ====================

    @Test
    void testQuantityLength_Equality() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCH);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testQuantityLength_EqualityInequal() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(2.0, LengthUnit.FEET);
        assertFalse(q1.equals(q2));
    }

    @Test
    void testQuantityLength_ConvertTo() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength result = q1.convertTo(LengthUnit.INCH);
        assertEquals(12.0, result.getValue());
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    void testQuantityLength_ConvertToYards() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(3.0, LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength result = q1.convertTo(LengthUnit.YARD);
        assertEquals(1.0, result.getValue(), 1e-6);
        assertEquals(LengthUnit.YARD, result.getUnit());
    }

    @Test
    void testQuantityLength_Add() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCH);
        QuantityMeasurementApp.QuantityLength result = q1.add(q2);
        assertEquals(2.0, result.getValue());
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testQuantityLength_AddWithTargetUnit_Feet() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCH);
        QuantityMeasurementApp.QuantityLength result = q1.add(q2, LengthUnit.FEET);
        assertEquals(2.0, result.getValue());
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testQuantityLength_AddWithTargetUnit_Inches() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCH);
        QuantityMeasurementApp.QuantityLength result = q1.add(q2, LengthUnit.INCH);
        assertEquals(24.0, result.getValue());
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    void testQuantityLength_AddWithTargetUnit_Yards() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCH);
        QuantityMeasurementApp.QuantityLength result = q1.add(q2, LengthUnit.YARD);
        assertEquals(0.667, result.getValue(), 1e-3);
        assertEquals(LengthUnit.YARD, result.getUnit());
    }

    @Test
    void testQuantityLength_AddWithTargetUnit_Centimeters() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.INCH);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.INCH);
        QuantityMeasurementApp.QuantityLength result = q1.add(q2, LengthUnit.CENTIMETER);
        assertEquals(5.08, result.getValue(), 1e-6);
        assertEquals(LengthUnit.CENTIMETER, result.getUnit());
    }

    @Test
    void testQuantityLength_AddCommutativity() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCH);
        QuantityMeasurementApp.QuantityLength result1 = q1.add(q2, LengthUnit.YARD);
        QuantityMeasurementApp.QuantityLength result2 = q2.add(q1, LengthUnit.YARD);
        assertEquals(result1.getValue(), result2.getValue(), 1e-6);
    }

    @Test
    void testQuantityLength_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityMeasurementApp.QuantityLength(1.0, null));
    }

    @Test
    void testQuantityLength_InvalidValue_NaN() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityMeasurementApp.QuantityLength(Double.NaN, LengthUnit.FEET));
    }

    @Test
    void testQuantityLength_InvalidValue_Infinity() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityMeasurementApp.QuantityLength(Double.POSITIVE_INFINITY, LengthUnit.FEET));
    }

    @Test
    void testQuantityLength_AddNullOperand() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q1.add(null));
    }

    @Test
    void testQuantityLength_AddNullTargetUnit() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCH);
        assertThrows(IllegalArgumentException.class, () -> q1.add(q2, null));
    }

    @Test
    void testQuantityLength_ConvertToNullUnit() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q1.convertTo(null));
    }

    // ==================== UC8: Refactored Design Tests ====================

    @Test
    void testRefactoredDesign_EqualityWithUnitDelegation() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCH);
        // Tests that equals() uses unit.convertToBaseUnit() delegation
        assertTrue(q1.equals(q2));
    }

    @Test
    void testRefactoredDesign_ConversionWithUnitDelegation() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(36.0, LengthUnit.INCH);
        QuantityMeasurementApp.QuantityLength result = q1.convertTo(LengthUnit.YARD);
        // Tests that convertTo() uses unit.convertToBaseUnit() and unit.convertFromBaseUnit()
        assertEquals(1.0, result.getValue(), 1e-6);
    }

    @Test
    void testRefactoredDesign_AdditionWithUnitDelegation() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.YARD);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(3.0, LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength result = q1.add(q2, LengthUnit.YARD);
        // Tests that add() uses unit.convertToBaseUnit() and unit.convertFromBaseUnit()
        assertEquals(2.0, result.getValue(), 1e-6);
    }

    @Test
    void testRoundTripConversion() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(5.0, LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength converted = q1.convertTo(LengthUnit.INCH);
        QuantityMeasurementApp.QuantityLength roundTrip = converted.convertTo(LengthUnit.FEET);
        assertEquals(5.0, roundTrip.getValue(), 1e-6);
    }

    @Test
    void testComplexArithmetic() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(2.54, LengthUnit.CENTIMETER);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.INCH);
        // q1 and q2 should be approximately equal
        assertTrue(q1.equals(q2));
    }

    @Test
    void testLengthUnitIndependence() {
        // This test confirms that adding a new unit to LengthUnit enum
        // requires no changes to QuantityLength class
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCH);
        // The add() method works without any modification
        QuantityMeasurementApp.QuantityLength result = q1.add(q2, LengthUnit.FEET);
        assertEquals(2.0, result.getValue());
    }

    @Test
    void testUnitImmutability() {
        // Verify that LengthUnit enum constants are immutable
        LengthUnit unit1 = LengthUnit.FEET;
        LengthUnit unit2 = LengthUnit.FEET;
        assertSame(unit1, unit2); // Same instance (enums are singletons)
    }
}

