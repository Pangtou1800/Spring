package pt.joja.lab.impl;

import pt.joja.lab.Calculator;

public class CalculatorClassicImpl implements Calculator {
    @Override
    public int add(int i, int j) {
        System.out.println("Calculating: add(" + i + ", " + j + ")");
        return i + j;
    }

    @Override
    public int sub(int i, int j) {
        System.out.println("Calculating: sub(" + i + ", " + j + ")");
        return i - j;
    }

    @Override
    public int mul(int i, int j) {
        System.out.println("Calculating: mul(" + i + ", " + j + ")");
        return i * j;
    }

    @Override
    public int div(int i, int j) {
        System.out.println("Calculating: div(" + i + ", " + j + ")");
        return i / j;
    }
}
