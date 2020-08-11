package pt.joja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.joja.dao.BookDao;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    public void save(){
        System.out.println("BookService调用DAO保存图书！");
        bookDao.saveBook();
    }

    @Override
    public String toString() {
        return "BookService{}";
    }
}
