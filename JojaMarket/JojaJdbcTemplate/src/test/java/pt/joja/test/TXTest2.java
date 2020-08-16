package pt.joja.test;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pt.joja.service.BookService;
import pt.joja.service.BookServiceXml;
import pt.joja.service.MulService;

public class TXTest2 {

    ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void test01() {
        BookService bookService = ioc.getBean("bookService", BookService.class);
        bookService.checkout("Tom", "ISBN-001", 3);
        System.out.println("结账成功");
    }

    @Test
    public void test02() {
        BookService bookService = ioc.getBean("bookService", BookService.class);
        int price = bookService.getPrice();
        System.out.println(price);
    }

    @Test
    public void test03() {
        MulService mulService = ioc.getBean("mulService", MulService.class);
        mulService.mulTx();
    }

    @Test
    public void test04() {
        BookServiceXml bookService = ioc.getBean("bookServiceXml", BookServiceXml.class);
        bookService.checkout("Tom", "ISBN-001", 3);
        System.out.println("结账成功");
    }
}
