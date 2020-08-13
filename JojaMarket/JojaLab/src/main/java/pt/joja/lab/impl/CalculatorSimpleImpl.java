package pt.joja.lab.impl;

import org.springframework.stereotype.Component;
import pt.joja.lab.Calculator;

@Component
public class CalculatorSimpleImpl implements Calculator {
    @Override
    public int add(int i, int j) {
        System.out.println("add...");
        return i + j;
    }

    @Override
    public int sub(int i, int j) {
        System.out.println("sub...");
        return i - j;
    }

    @Override
    public int mul(int i, int j) {
        System.out.println("mul");
        return i * j;
    }

    @Override
    public int div(int i, int j) {
        System.out.println("div");
        return i / j;
    }
}
