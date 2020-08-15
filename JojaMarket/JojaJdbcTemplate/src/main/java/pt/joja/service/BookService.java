package pt.joja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.joja.dao.BookDao;

@Service
public class BookService {

    @Autowired
    BookDao bookDao;

    @Transactional
    public void checkout(String username, String isbn, int amount) {

        // 1. 减库存
        bookDao.updateStock(isbn, amount);

        // 2. 减余额
        int price = bookDao.getPrice(isbn);
        int totalPrice = price * amount;
        bookDao.updateBalance(username, totalPrice);

    }
}
