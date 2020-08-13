package pt.joja.lab;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class LogUtil {

    @Before("execution(public int pt.joja.lab.impl.CalculatorSimpleImpl.*(int, int))")
    public static void log() {
        System.out.println("LogUtil.log() before..");
    }

    @After("execution(public int pt.joja.lab.impl.CalculatorSimpleImpl.*(int, int))")
    public static void log2() {
        System.out.println("LogUtil.log() after..");
    }

    @AfterReturning("execution(public int pt.joja.lab.impl.CalculatorSimpleImpl.*(int, int))")
    public static void log3() {
        System.out.println("LogUtil.log() afterReturn..");
    }

    @AfterThrowing("execution(public int pt.joja.lab.impl.CalculatorSimpleImpl.*(int, int))")
    public static void log4() {
        System.out.println("LogUtil.log() exception..");
    }

    public static void logStart(Method method, Object... args) {
        String argStr = Arrays.asList(args).toString();
        System.out.println("[LogUtil]: " + method.getName() + "(" + argStr.substring(1, args.length - 1) + ")");
    }

    public static void logReturn(Method method, Object result) {
        System.out.println("[LogUtil]: " + method.getName() + " => " + result);
    }

    public static void logException(Method method, Exception e) {
        System.out.println("[LogUtil]: " + method.getName() + " !! " + e.toString());
    }


}
