package com.quantit;

public class QuantityMeasurementApp {

    public enum LengthUnit {
        FEET(1.0),
        INCH(1.0/12.0),
        YARD(3.0),
        CENTIMETER(0.0328084);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double toBaseUnit(double value) {
            return value * conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null || !Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid input");
            }
            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        public double convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }
            double baseValue = this.unit.toBaseUnit(this.value);
            return baseValue / targetUnit.getConversionFactor();
        }

        public QuantityLength add(QuantityLength other) {
            if (other == null) {
                throw new IllegalArgumentException("Operand cannot be null");
            }
            double thisBase = this.unit.toBaseUnit(this.value);
            double otherBase = other.unit.toBaseUnit(other.value);
            double sumBase = thisBase + otherBase;
            double sumInTargetUnit = sumBase / this.unit.getConversionFactor();
            return new QuantityLength(sumInTargetUnit, this.unit);
        }

        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
            if (other == null || targetUnit == null) {
                throw new IllegalArgumentException("Operands and target unit cannot be null");
            }
            double thisBase = this.unit.toBaseUnit(this.value);
            double otherBase = other.unit.toBaseUnit(other.value);
            double sumBase = thisBase + otherBase;
            double sumInTargetUnit = sumBase / targetUnit.getConversionFactor();
            return new QuantityLength(sumInTargetUnit, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            QuantityLength other = (QuantityLength) obj;
            double thisBase = this.unit.toBaseUnit(this.value);
            double otherBase = other.unit.toBaseUnit(other.value);
            return Double.compare(thisBase, otherBase) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit.name();
        }
    }

    public static void main(String[] args) {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);
        System.out.println(q1.add(q2, LengthUnit.YARD));
    }
}
