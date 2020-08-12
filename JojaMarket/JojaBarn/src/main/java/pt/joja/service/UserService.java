package pt.joja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.joja.bean.User;
import pt.joja.dao.UserDao;

@Service
public class UserService extends BaseService<User> {

//    @Autowired
//    private UserDao userDao;
//
//    public void save() {
//        userDao.save();
//    }
}
