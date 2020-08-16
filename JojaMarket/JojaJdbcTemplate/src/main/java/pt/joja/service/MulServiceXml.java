package pt.joja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class MulServiceXml {

    private BookService bookService;

    public BookService getBookService() {
        return bookService;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public void mulTx() {
        bookService.checkout("Tom", "ISBN-001", 4);
        bookService.updatePrice("ISBN-002", 505);
        int num = 10/0;
    }
}
