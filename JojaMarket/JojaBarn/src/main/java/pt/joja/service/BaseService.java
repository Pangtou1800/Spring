package pt.joja.service;

import org.springframework.beans.factory.annotation.Autowired;
import pt.joja.dao.BaseDao;

public class BaseService<T> {

    @Autowired
    protected BaseDao<T> baseDao;

    public void save() {
        System.out.println("自动注入了：" + baseDao);
        baseDao.save();
    }


}
