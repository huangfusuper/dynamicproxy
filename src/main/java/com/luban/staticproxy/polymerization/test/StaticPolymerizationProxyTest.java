package com.luban.staticproxy.polymerization.test;

import com.luban.staticproxy.polymerization.dao.UserDao;
import com.luban.staticproxy.polymerization.dao.impl.UserDaoImpl;
import com.luban.staticproxy.polymerization.proxy.UserLogDaoImpl;
import org.junit.Test;

/**
 * 基于聚合的静态代理类测试
 * @author 皇甫
 */
public class StaticPolymerizationProxyTest {
    @Test
    public void original(){
        UserDao userDao = new UserDaoImpl();
        userDao.query();
    }
    @Test
    public void testProxy(){
        UserDao target = new UserDaoImpl();
        UserDao proxy = new UserLogDaoImpl(target);
        proxy.query();
    }
}
