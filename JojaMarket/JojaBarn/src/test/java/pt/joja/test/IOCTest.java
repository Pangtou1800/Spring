package pt.joja.test;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pt.joja.dao.BookDao;
import pt.joja.service.BookService;
import pt.joja.servlet.BookServlet;

public class IOCTest {

    private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void test02(){
        BookServlet servlet = context.getBean(BookServlet.class);
        servlet.doGet();
    }

    @Test
    public void test01() {
        BookServlet bookServlet = context.getBean("bookController", BookServlet.class);
        System.out.println(bookServlet);
        BookServlet bookServlet2 = context.getBean("bookController", BookServlet.class);
        System.out.println(bookServlet2);
        System.out.println(bookServlet == bookServlet2);
        BookService bookService = context.getBean("bookService", BookService.class);
        System.out.println(bookService);
        BookDao bookDao = context.getBean("bookDao", BookDao.class);
        System.out.println(bookDao);
    }
}
