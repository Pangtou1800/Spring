package pt.joja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.joja.dao.BookDao;

@Service
public class BookServiceExtend extends BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public void save(){
        System.out.println("BookServiceExtend调用DAO保存图书！");
        bookDao.saveBook();
    }
}
