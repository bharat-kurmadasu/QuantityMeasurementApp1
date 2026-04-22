package com.quantit;

import java.util.Objects;

/**
 * Represents an immutable weight quantity.
 * Supports equality comparison, conversion between weight units, and addition.
 * All operations are based on the base unit kilogram.
 */
public final class QuantityWeight {
    private static final double EPSILON = 1e-6;

    private final double value;
    private final WeightUnit unit;

    /**
     * Creates a weight quantity.
     *
     * @param value the numeric magnitude
     * @param unit  the weight unit; must not be {@code null}
     * @throws IllegalArgumentException if {@code unit} is {@code null} or the value is NaN/Infinite
     */
    public QuantityWeight(double value, WeightUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
        this.value = value;
        this.unit = unit;
    }

    /** Returns the raw value stored in this object. */
    public double getValue() {
        return value;
    }

    /** Returns the weight unit of this quantity. */
    public WeightUnit getUnit() {
        return unit;
    }

    /**
     * Converts this quantity to the specified target unit.
     *
     * @param targetUnit the unit to convert to; must not be {@code null}
     * @return a new {@code QuantityWeight} in the target unit
     */
    public QuantityWeight convertTo(WeightUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double baseValue = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);
        return new QuantityWeight(converted, targetUnit);
    }

    /**
     * Adds another weight quantity to this one.
     * The result is expressed in the unit of this instance.
     *
     * @param other the other weight to add; must not be {@code null}
     * @return a new {@code QuantityWeight} representing the sum
     */
    public QuantityWeight add(QuantityWeight other) {
        return add(this, other, this.unit);
    }

    /**
     * Static helper that adds two quantities and expresses the result in the supplied target unit.
     *
     * @param q1         first operand
     * @param q2         second operand
     * @param targetUnit unit for the result; must not be {@code null}
     * @return new {@code QuantityWeight} with summed value in {@code targetUnit}
     */
    public static QuantityWeight add(QuantityWeight q1, QuantityWeight q2, WeightUnit targetUnit) {
        if (q1 == null || q2 == null) {
            throw new IllegalArgumentException("Operands cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double base1 = q1.unit.convertToBaseUnit(q1.value);
        double base2 = q2.unit.convertToBaseUnit(q2.value);
        double sumBase = base1 + base2;
        double resultValue = targetUnit.convertFromBaseUnit(sumBase);
        return new QuantityWeight(resultValue, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        QuantityWeight other = (QuantityWeight) obj;
        double thisBase = unit.convertToBaseUnit(value);
        double otherBase = other.unit.convertToBaseUnit(other.value);
        return Math.abs(thisBase - otherBase) < EPSILON;
    }

    @Override
    public int hashCode() {
        double base = unit.convertToBaseUnit(value);
        return Objects.hash(Math.round(base / EPSILON));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}
