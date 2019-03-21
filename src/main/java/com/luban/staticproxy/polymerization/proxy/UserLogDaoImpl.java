package com.luban.staticproxy.polymerization.proxy;

import com.luban.staticproxy.polymerization.dao.UserDao;

/**
 * 增强类  基于聚合的代理类
 * @author 皇甫
 */
public class UserLogDaoImpl implements UserDao {
    private UserDao userDao;
    public UserLogDaoImpl(UserDao userDao){
        this.userDao = userDao;
    }

    public void query() {
        System.out.println("-----------log-------------");
        userDao.query();
    }
}
