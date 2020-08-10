package pt.joja.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {

    private String beanName;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + " before initialize...");
        this.beanName = beanName;
        return bean;
    }

    public void init() {
        System.out.println(beanName + " initializing...");
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + " after initialize...");
        return bean;
    }
}
