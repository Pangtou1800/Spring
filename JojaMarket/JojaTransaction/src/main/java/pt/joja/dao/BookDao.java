package pt.joja.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 1. 减余额
     */
    public void updateBalance(String userName, int amount) {
        String sql = "update account set balance = balance - ? where username=?";
        jdbcTemplate.update(sql, amount, userName);
    }

    /**
     * 2. 获取某本图书的价格
     */
    public int getPrice(String isbn) {
        String sql = "select price from book where isbn = ?";
        int price = jdbcTemplate.queryForObject(sql, Integer.class, isbn);
        return price;
    }

    /**
     * 3. 减库存
     */
    public void updateStock(String isbn, int stock) {
        String sql = "update book_stock set stock = stock - ? where isbn = ?";
        jdbcTemplate.update(sql, stock, isbn);
    }
}
