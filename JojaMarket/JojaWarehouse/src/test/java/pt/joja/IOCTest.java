package pt.joja;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pt.joja.bean.AirPlane;
import pt.joja.bean.Book;
import pt.joja.bean.Car;
import pt.joja.bean.Person;

public class IOCTest {

    ApplicationContext ioc = new ClassPathXmlApplicationContext("ioc3.xml");

    @Test
    public void test09 () {
        System.out.println("Container completed.");
        AirPlane ap = ioc.getBean("plane2", AirPlane.class);
        System.out.println(ap);

        AirPlane ap2 = ioc.getBean("plane3", AirPlane.class);
        System.out.println(ap2);

        AirPlane ap3 = ioc.getBean("myFactoryBeanImpl", AirPlane.class);
        System.out.println(ap3);

    }

    @Test
    public void test08() {
        System.out.println("Container completed.");
        Book book = ioc.getBean("book", Book.class);
        Book book2 = ioc.getBean("book", Book.class);
        System.out.println(book == book2);
        Book book3 = ioc.getBean("book2", Book.class);
        Book book4 = ioc.getBean("book2", Book.class);
        System.out.println(book3 == book4);
    }

    @Test
    public void test06() {
        Person person = ioc.getBean("person06", Person.class);
        System.out.println(person);

        person = ioc.getBean("person05", Person.class);
        System.out.println(person);
    }

    @Test
    public void test05() {
        Person person = ioc.getBean("person04", Person.class);
        System.out.println(person.getCar());
        Car car = ioc.getBean("car01", Car.class);
        System.out.println(car);

    }

    @Test
    public void test04() {
        Person person = ioc.getBean("person01", Person.class);
        System.out.println(person);

        Car car2 = ioc.getBean("car01", Car.class);
        System.out.println(car2);
        System.out.println(car2 == person.getCar());

        Person person2 = ioc.getBean("person02", Person.class);
        System.out.println(person2);
//        Book book1 = ioc.getBean("book01", Book.class);
//        System.out.println(book1);

        Person person3 = ioc.getBean("person03", Person.class);
        System.out.println(person3);
    }

    @Test
    public void test03() {
        Person person = ioc.getBean("person3", Person.class);
        System.out.println(person);

        Person person2 = ioc.getBean("person4", Person.class);
        System.out.println(person2);

        Person person3 = ioc.getBean("person6", Person.class);
        System.out.println(person3);
    }

    @Test
    public void test02() {
//        System.out.println(ioc.getBean(Person.class));

        Person person2 = ioc.getBean("person2", Person.class);
        System.out.println(person2);
    }

    /**
     * 从容器中拿到组件
     */
    @Test
    public void test() {
        // ApplicationContext代表IOC容器
        // ClassPathXmlApplicationContext:当前的xml配置文件在ClassPath下
        // 根据spring配置文件得到IOC容器对象
        ApplicationContext ioc = new ClassPathXmlApplicationContext("ioc.xml");

        System.out.println("ioc container created");

        // 容器帮我们创建好对象了
        Person bean = (Person) ioc.getBean("person1");
        Person bean2 = (Person) ioc.getBean("person1");

        System.out.println(bean);
        System.out.println(bean == bean2);
    }

}
