package com.quantit;

public enum LengthUnit {
    FEET(1.0),
    INCH(1.0 / 12.0),
    YARD(3.0),
    CENTIMETER(0.0328084);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    /**
     * Converts a value in this unit to the base unit (feet).
     * @param value the value in this unit
     * @return the equivalent value in feet
     */
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    /**
     * Converts a value from the base unit (feet) to this unit.
     * @param baseValue the value in feet
     * @return the equivalent value in this unit
     */
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }
}
