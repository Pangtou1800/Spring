package pt.joja.test;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pt.joja.lab.Calculator;
import pt.joja.lab.impl.CalculatorClassicImpl;
import pt.joja.lab.impl.CalculatorSimpleImpl;
import pt.joja.lab.proxy.CalculatorProxy;

import java.util.Arrays;

public class AOPTest {

    ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void test2() {
        Calculator calculator = ioc.getBean(Calculator.class);
        System.out.println(calculator);
        System.out.println(calculator.getClass());
        calculator.add(1, 2);
        calculator.sub(4, 3);
        calculator.mul(5, 6);
        calculator.div(8, 7);
        calculator.div(9, 0);
    }

    @Test
    public void test01() {
        Calculator calculator = new CalculatorClassicImpl();
        calculator.add(1, 2);
        calculator.sub(3, 4);
        calculator.mul(5, 6);
        calculator.div(7, 8);

        System.out.println("============");

        Calculator simpleCalculator = new CalculatorSimpleImpl();
        Calculator proxy = CalculatorProxy.getProxy(simpleCalculator);
        proxy.add(1, 2);
        proxy.sub(3, 4);
        proxy.mul(5, 6);
        proxy.div(7, 8);

        System.out.println("============");

        Calculator simpleCalculator2 = new CalculatorSimpleImpl();
        Calculator proxy2 = CalculatorProxy.getProxy2(simpleCalculator);
        proxy2.add(1, 2);
        proxy2.sub(3, 4);
        proxy2.mul(5, 6);
        proxy2.div(7, 8);
        System.out.println(proxy2.getClass());
        System.out.println(Arrays.toString(proxy2.getClass().getInterfaces()));
    }
}
