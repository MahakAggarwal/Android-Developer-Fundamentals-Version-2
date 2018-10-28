package me.mahakagg.simplecalcHW;

import java.lang.Math.*;

public class Calculator {
    // Available operations
    public enum Operator {ADD, SUB, DIV, MUL, POW}

    /**
     * Addition operation
     */
    public double addition(double firstOperand, double secondOperand) {
        return firstOperand + secondOperand;
    }

    /**
     * Subtract operation
     */
    public double subtraction(double firstOperand, double secondOperand) {
        return firstOperand - secondOperand;
    }

    /**
     * Divide operation
     */
    public double division(double firstOperand, double secondOperand) {
        return firstOperand / secondOperand;
    }

    /**
     * Multiply operation
     */
    public double multiplication(double firstOperand, double secondOperand) {
        return firstOperand * secondOperand;
    }

    /**
     * power operation
     */
    public double power(double firstOperand, double secondOperand) {
        return Math.pow(firstOperand, secondOperand);
    }
}
