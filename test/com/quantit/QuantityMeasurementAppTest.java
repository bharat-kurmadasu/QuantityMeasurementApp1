package com.quantit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // --- UC9: Weight Measurement Tests ---

    @Test
    void testEquality_KilogramToKilogram_SameValue() {
        assertEquals(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
        );
    }

    @Test
    void testEquality_KilogramToKilogram_DifferentValue() {
        assertNotEquals(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(2.0, WeightUnit.KILOGRAM)
        );
    }

    @Test
    void testEquality_KilogramToGram_EquivalentValue() {
        assertEquals(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1000.0, WeightUnit.GRAM)
        );
    }

    @Test
    void testEquality_GramToKilogram_EquivalentValue() {
        assertEquals(
                new QuantityWeight(1000.0, WeightUnit.GRAM),
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
        );
    }

    @Test
    void testEquality_KilogramToPound_EquivalentValue() {
        // 1 kg = 1 / 0.453592 pounds approx 2.20462
        assertEquals(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(2.204624420889257, WeightUnit.POUND)
        );
    }

    @Test
    void testEquality_WeightVsLength_Incompatible() {
        QuantityWeight weight = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityLength length = new QuantityLength(1.0, LengthUnit.FEET);
        assertNotEquals(weight, length);
    }

    @Test
    void testEquality_NullComparison() {
        QuantityWeight weight = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        assertNotEquals(null, weight);
    }

    @Test
    void testEquality_SameReference() {
        QuantityWeight weight = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        assertEquals(weight, weight);
    }

    @Test
    void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityWeight(1.0, null));
    }

    @Test
    void testEquality_ZeroValue() {
        assertEquals(
                new QuantityWeight(0.0, WeightUnit.KILOGRAM),
                new QuantityWeight(0.0, WeightUnit.GRAM)
        );
    }

    @Test
    void testConversion_PoundToKilogram() {
        QuantityWeight result = new QuantityWeight(2.204624420889257, WeightUnit.POUND)
                .convertTo(WeightUnit.KILOGRAM);
        assertEquals(1.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_KilogramToPound() {
        QuantityWeight result = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.POUND);
        assertEquals(2.204624420889257, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_SameUnit() {
        QuantityWeight result = new QuantityWeight(5.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.KILOGRAM);
        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_SameUnit_KilogramPlusKilogram() {
        QuantityWeight result = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(2.0, WeightUnit.KILOGRAM));
        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    void testAddition_CrossUnit_KilogramPlusGram() {
        QuantityWeight result = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(1000.0, WeightUnit.GRAM));
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Gram() {
        QuantityWeight result = QuantityWeight.add(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1000.0, WeightUnit.GRAM),
                WeightUnit.GRAM
        );
        assertEquals(2000.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    void testAddition_Commutativity() {
        QuantityWeight q1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight q2 = new QuantityWeight(1000.0, WeightUnit.GRAM);
        
        QuantityWeight sum1 = q1.add(q2); // Result in KG
        QuantityWeight sum2 = q2.add(q1); // Result in GRAM
        
        assertEquals(new QuantityWeight(2.0, WeightUnit.KILOGRAM), sum1);
        assertEquals(new QuantityWeight(2000.0, WeightUnit.GRAM), sum2);
        assertEquals(sum1, sum2);
    }

    // --- Length Unit Compatibility (from previous UCs) ---

    @Test
    void testLengthEquality_FeetToInch() {
        assertEquals(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCH)
        );
    }

    @Test
    void testLengthAddition_FeetPlusInch() {
        QuantityLength result = new QuantityLength(1.0, LengthUnit.FEET)
                .add(new QuantityLength(12.0, LengthUnit.INCH));
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }
}