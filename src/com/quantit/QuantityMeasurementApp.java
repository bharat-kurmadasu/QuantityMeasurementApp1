package com.quantit;

public class QuantityMeasurementApp {

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

        /**
         * Converts this quantity to the target unit.
         * @param targetUnit the target unit
         * @return a new QuantityLength in the target unit
         */
        public QuantityLength convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }
            double baseValue = unit.convertToBaseUnit(value);
            double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
            return new QuantityLength(convertedValue, targetUnit);
        }

        /**
         * Adds this quantity to another and returns result in the same unit as this quantity.
         * @param other the quantity to add
         * @return a new QuantityLength representing the sum
         */
        public QuantityLength add(QuantityLength other) {
            if (other == null) {
                throw new IllegalArgumentException("Operand cannot be null");
            }
            double thisBase = unit.convertToBaseUnit(value);
            double otherBase = other.unit.convertToBaseUnit(other.value);
            double sumBase = thisBase + otherBase;
            double sumInTargetUnit = unit.convertFromBaseUnit(sumBase);
            return new QuantityLength(sumInTargetUnit, unit);
        }

        /**
         * Adds this quantity to another and returns result in the specified target unit.
         * @param other the quantity to add
         * @param targetUnit the target unit for the result
         * @return a new QuantityLength representing the sum in the target unit
         */
        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
            if (other == null || targetUnit == null) {
                throw new IllegalArgumentException("Operands and target unit cannot be null");
            }
            double thisBase = unit.convertToBaseUnit(value);
            double otherBase = other.unit.convertToBaseUnit(other.value);
            double sumBase = thisBase + otherBase;
            double sumInTargetUnit = targetUnit.convertFromBaseUnit(sumBase);
            return new QuantityLength(sumInTargetUnit, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            QuantityLength other = (QuantityLength) obj;
            double thisBase = unit.convertToBaseUnit(value);
            double otherBase = other.unit.convertToBaseUnit(other.value);
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
