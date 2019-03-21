package com.luban.staticproxy.polymerization.dao.impl;

import com.luban.staticproxy.polymerization.dao.UserDao;

/**
 * 聚合代理实现类  目标类
 * @author 皇甫
 */
public class UserDaoImpl implements UserDao {

    public void query() {
        System.out.println("假传查询数据库");
    }
}
