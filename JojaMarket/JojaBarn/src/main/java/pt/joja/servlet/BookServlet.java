package pt.joja.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import pt.joja.dao.BookDao;
import pt.joja.service.BookService;

@Controller
public class BookServlet {

    @Autowired
    private BookService bookService;

    public void doGet() {
        System.out.println("BookServlet调用BookService！");
        bookService.save();
    }

    @Autowired
    public void hahaha(@Qualifier("bookDao") BookDao dao) {
        System.out.println(dao);
    }

    @Override
    public String toString() {
        return "BookServlet{}";
    }
}
