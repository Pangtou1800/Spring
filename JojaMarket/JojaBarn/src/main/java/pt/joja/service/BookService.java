package pt.joja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.joja.bean.Book;
import pt.joja.dao.BookDao;

import javax.annotation.Resource;

@Service
public class BookService extends BaseService<Book> {


//    // @Resource
//    @Autowired
//    private BookDao bookDao;

//    public void save(){
//        System.out.println("BookService调用DAO保存图书！");
//        bookDao.save();
//    }

    @Override
    public String toString() {
        return "BookService{}";
    }
}
