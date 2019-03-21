package com.luban.staticproxy.inherit.test;

import com.luban.staticproxy.inherit.dao.UserDaoImpl;
import com.luban.staticproxy.inherit.proxy.UserLogDaoImpl;
import org.junit.Test;

/**
 * 基于继承代理类的测试类
 * @author 皇甫
 */
public class StaticProxyTest {
    /**
     * 目标方法测试
     */
    @Test
    public void original(){
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.query();
    }

    /**
     * 代理方法测试
     */
    @Test
    public void testProxy(){
        UserLogDaoImpl userLogDao = new UserLogDaoImpl();
        userLogDao.query();
    }
}
