package pt.joja.lab;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

import java.util.Arrays;

public class LogUtil {

    public void logStart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        System.out.println("[LogUtil@Before]" + signature.getName() + "方法开始执行，参数:" + Arrays.asList(args));
    }

    public void logReturn(JoinPoint joinPoint, Object result) {
        Signature signature = joinPoint.getSignature();
        System.out.println("[LogUtil@AfterReturning]" + signature.getName() + "方法执行结束，结果是：" + result);
    }

    public void logException(JoinPoint joinPoint, Exception exception) {
        Signature signature = joinPoint.getSignature();
        System.out.println("[LogUtil@AfterThrowing]" + signature.getName() + "方法抛出异常，异常信息是：" + exception);
    }

    public void logEnd(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println("[LogUtil@After]" + signature.getName() + "方法结束了");
    }

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
