package edu.info448.calculator;

/**
 * Utility class for Calculator to perform the actual calculations.
 */
public class Calculator {

    // Available operations
    public enum Operator {ADD, SUB, DIV, MUL}

    // Stored number
    private double value;

    public Calculator() {
        setValue(0.0);
    }

    public void setValue(double val) {
        value = val;
    }

    /**
     * Addition operation
     */
    public double add(double operand) {
        value += operand;
        return value;
    }

    /**
     * Subtract operation
     */
    public double sub(double operand) {
        value -= operand;
        return value;
    }

    /**
     * Divide operation
     */
    public double div(double operand) {
        value /= operand;
        return value;
    }

    /**
     * Multiply operation
     */
    public double mul(double operand) {
        value *= operand;
        return value;
    }
}
