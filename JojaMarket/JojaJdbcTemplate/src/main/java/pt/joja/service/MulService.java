package pt.joja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MulService {

    @Autowired
    private BookService bookService;

    @Transactional
    public void mulTx() {
        bookService.checkout("Tom", "ISBN-001", 4);
        bookService.updatePrice("ISBN-002", 505);
        int num = 10/0;
    }
}
