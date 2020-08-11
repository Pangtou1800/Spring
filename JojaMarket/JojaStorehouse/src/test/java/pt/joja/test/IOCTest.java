package pt.joja.test;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pt.joja.bean.Book;
import pt.joja.bean.Person;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class IOCTest {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext03.xml");

    @Test
    public void test5() {
        Person person = applicationContext.getBean("person5", Person.class);
        System.out.println(person);
    }

    @Test
    public void test4() {
        Person person = applicationContext.getBean("person2", Person.class);
        System.out.println(person);
        person = applicationContext.getBean("person3", Person.class);
        System.out.println(person);
        person = applicationContext.getBean("person4", Person.class);
        System.out.println(person);
    }


    @Test
    public void test3() {
        // 1. 从容器中拿到连接池
        DataSource dataSource = applicationContext.getBean("dataSource", DataSource.class);
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            System.out.println(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test() {
        // ApplicationContext没有close()方法，但是之后的继承类就有了
        ConfigurableApplicationContext cfgAppContext = (ConfigurableApplicationContext) applicationContext;
        cfgAppContext.close();
    }

    @Test
    public void test2() {
        Book book = applicationContext.getBean("book02", Book.class);

        ConfigurableApplicationContext cfgAppContext = (ConfigurableApplicationContext) applicationContext;
        cfgAppContext.close();

    }

}
