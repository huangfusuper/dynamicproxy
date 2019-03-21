package com.luban.staticproxy.inherit.proxy;

import com.luban.staticproxy.inherit.dao.UserDaoImpl;

/**
 * 基于继承添加日志功能
 * 目标类的代理类
 * @author 皇甫
 */
public class UserLogDaoImpl extends UserDaoImpl {
    @Override
    public void query() {
        System.out.println("----------log-------------");
        super.query();
    }
}
