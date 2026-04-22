package com.quantit;

import java.util.Objects;

/**
 * Represents an immutable length quantity.
 * Supports equality comparison, conversion between length units, and addition.
 * All operations are based on the base unit feet.
 */
public final class QuantityLength {
    private static final double EPSILON = 1e-6;

    private final double value;
    private final LengthUnit unit;

    /**
     * Creates a length quantity.
     *
     * @param value the numeric magnitude
     * @param unit  the length unit; must not be {@code null}
     * @throws IllegalArgumentException if {@code unit} is {@code null} or the value is NaN/Infinite
     */
    public QuantityLength(double value, LengthUnit unit) {
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

    /** Returns the length unit of this quantity. */
    public LengthUnit getUnit() {
        return unit;
    }

    /**
     * Converts this quantity to the specified target unit.
     *
     * @param targetUnit the unit to convert to; must not be {@code null}
     * @return a new {@code QuantityLength} in the target unit
     */
    public QuantityLength convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double baseValue = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);
        return new QuantityLength(converted, targetUnit);
    }

    /**
     * Adds another length quantity to this one.
     * The result is expressed in the unit of this instance.
     *
     * @param other the other length to add; must not be {@code null}
     * @return a new {@code QuantityLength} representing the sum
     */
    public QuantityLength add(QuantityLength other) {
        return add(this, other, this.unit);
    }

    /**
     * Static helper that adds two quantities and expresses the result in the supplied target unit.
     *
     * @param q1         first operand
     * @param q2         second operand
     * @param targetUnit unit for the result; must not be {@code null}
     * @return new {@code QuantityLength} with summed value in {@code targetUnit}
     */
    public static QuantityLength add(QuantityLength q1, QuantityLength q2, LengthUnit targetUnit) {
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
        return new QuantityLength(resultValue, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        QuantityLength other = (QuantityLength) obj;
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
