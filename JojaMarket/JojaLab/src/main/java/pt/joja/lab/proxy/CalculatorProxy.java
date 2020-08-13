package pt.joja.lab.proxy;

import pt.joja.lab.Calculator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CalculatorProxy {

    public static Calculator getProxy(final Calculator calculator) {
        ClassLoader classLoader = calculator.getClass().getClassLoader();
        Class<?>[] interfaces = calculator.getClass().getInterfaces();
        // 方法执行器
        InvocationHandler handler = new InvocationHandler() {
            /**
             *
             * @param proxy 代理对象；给jdk使用的，不要用它
             * @param method 当前将要执行的目标对象的方法
             * @param args 执行方法时的参数
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("Calculating: " + method.getName() + "(" + args[0] + ", " + args[1] + ")");
                // 执行真正的方法
                Object result = method.invoke(calculator, args);
                System.out.println("Calculating: result = " + result);
                // 返回方法的执行结果
                return result;
            }
        };

        Calculator proxy = (Calculator) Proxy.newProxyInstance(classLoader, interfaces, handler);
        return proxy;
    }

    public static <T> T getProxy2(T instance) {
        ClassLoader classLoader = instance.getClass().getClassLoader();
        Class<?>[] interfaces = instance.getClass().getInterfaces();
        InvocationHandler handler = new SimpleInvocationHandler<T>(instance);

        T proxy = (T) Proxy.newProxyInstance(classLoader, interfaces, handler);

        return proxy;
    }

    static class SimpleInvocationHandler<T> implements InvocationHandler {

        private T instance;

        SimpleInvocationHandler(T instance) {
            this.instance = instance;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(this.getClass().getName() + " working...");
            Object result = method.invoke(instance,args);
            return result;
        }
    }
}
