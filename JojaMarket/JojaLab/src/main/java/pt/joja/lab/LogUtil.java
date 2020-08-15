package pt.joja.lab;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
@Order(1)
public class LogUtil {

    @Pointcut("execution(* pt.joja.lab.impl.*.*(..))")
    public void myPoint() {}

    @Before("myPoint()")
    public void logStart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        System.out.println("[LogUtil@Before]"+signature.getName() + "方法开始执行，参数:" + Arrays.asList(args));
    }

    @AfterReturning(value = "execution(* pt.joja.lab.impl.*.*(..))", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        Signature signature = joinPoint.getSignature();
        System.out.println("[LogUtil@AfterReturning]"+signature.getName() + "方法执行结束，结果是：" + result);
    }

    @AfterThrowing(value = "execution(* pt.joja.lab.impl.*.*(..))", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        Signature signature = joinPoint.getSignature();
        System.out.println("[LogUtil@AfterThrowing]"+signature.getName() + "方法抛出异常，异常信息是：" + exception);
    }

    @After("execution(* pt.joja.lab.impl.*.*(..))")
    public void logEnd(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println("[LogUtil@After]"+signature.getName() + "方法结束了");
    }

//
//    @Before("execution(public int pt.joja.lab.impl.CalculatorSimpleImpl.*(int, int))")
//    public static void log() {
//        System.out.println("LogUtil.log() before..");
//    }
//
//    @After("execution(public int pt.joja.lab.impl.CalculatorSimpleImpl.*(int, int))")
//    public static void log2() {
//        System.out.println("LogUtil.log() after..");
//    }
//
//    @AfterReturning("execution(public int pt.joja.lab.impl.CalculatorSimpleImpl.*(int, int))")
//    public static void log3() {
//        System.out.println("LogUtil.log() afterReturn..");
//    }
//
//    @AfterThrowing("execution(public int pt.joja.lab.impl.CalculatorSimpleImpl.*(int, int))")
//    public static void log4() {
//        System.out.println("LogUtil.log() exception..");
//    }

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

    //@Around("myPoint()")
    public Object logAround2(ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        try {
            System.out.println("around2 before..");
            Object result = pjp.proceed(args);
            System.out.println("around2 afterReturn..");
            return result;
        } catch (Throwable throwable) {
            System.out.println("around2 afterThrowing..");
            throwable.printStackTrace();
            throw new RuntimeException(throwable);
        } finally {
            System.out.println("around2 after..");
        }
    }

    //@Around("myPoint()")
    public Object logAround3(ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        try {
            System.out.println("around3 before..");
            Object result = pjp.proceed(args);
            System.out.println("around3 afterReturn..");
            return result;
        } catch (Throwable throwable) {
            System.out.println("around3 afterThrowing..");
            throwable.printStackTrace();
            throw new RuntimeException(throwable);
        } finally {
            System.out.println("around3 after..");
        }
    }

    @Around("myPoint()")
    public Object logAround(ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        try {
            System.out.println("around before..");
            Object result = pjp.proceed(args);
            System.out.println("around afterReturn..");
            return result;
        } catch (Throwable throwable) {
            System.out.println("around afterThrowing..");
            throwable.printStackTrace();
            throw new RuntimeException(throwable);
        } finally {
            System.out.println("around after..");
        }
    }




}
