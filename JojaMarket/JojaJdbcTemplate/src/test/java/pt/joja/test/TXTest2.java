package pt.joja.test;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pt.joja.service.BookService;

public class TXTest2 {

    ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void test01() {
        BookService bookService = ioc.getBean("bookService", BookService.class);
        bookService.checkout("Tom","ISBN-001", 3);
        System.out.println("结账成功");
    }
}
