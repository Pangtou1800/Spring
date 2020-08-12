package pt.joja.dao;

import org.springframework.stereotype.Repository;
import pt.joja.bean.User;

@Repository
public class UserDao extends BaseDao<User> {

    @Override
    public void save() {
        System.out.println("用户保存了！");
    }

    @Override
    public String toString() {
        return "UserDao{}";
    }
}
