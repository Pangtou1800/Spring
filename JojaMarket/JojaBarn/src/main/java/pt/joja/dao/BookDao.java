package pt.joja.dao;

import org.springframework.stereotype.Repository;

@Repository
public class BookDao {

    public void saveBook() {
        System.out.println("图书保存了！");
    }

    @Override
    public String toString() {
        return "BookDao{}";
    }
}
