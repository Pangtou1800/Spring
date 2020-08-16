package pt.joja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pt.joja.dao.BookDao;

public class BookServiceXml {

    BookDao bookDao;

    public BookDao getBookDao() {
        return bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void checkout(String username, String isbn, int amount) {

        // 1. 减库存
        bookDao.updateStock(isbn, amount);

        // 2. 减余额
        int price = bookDao.getPrice(isbn);
        int totalPrice = price * amount;
        bookDao.updateBalance(username, totalPrice);

        // int num = 10 / 0;

    }

    public void updatePrice(String isbn, int price) {
        bookDao.updatePrice(isbn, price);
    }

    public int getPrice() {
        int price = bookDao.getPrice("ISBN-002");
        return price;
    }
}
