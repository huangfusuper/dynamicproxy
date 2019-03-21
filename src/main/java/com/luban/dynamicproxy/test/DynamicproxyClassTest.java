package com.luban.dynamicproxy.test;

import com.luban.dynamicproxy.utils.ProxyUtil;
import com.luban.staticproxy.polymerization.dao.UserDao;
import com.luban.staticproxy.polymerization.dao.impl.UserDaoImpl;
import org.junit.Test;

/**
 * @author 皇甫
 */
public class DynamicproxyClassTest {
    @Test
    public void test1(){
        UserDao o = (UserDao) ProxyUtil.newInstance(new UserDaoImpl());
        o.query();
    }
}
