package com.luban.staticproxy.inherit.dao;

/**
 * 基于继承的静态代理的目标类
 * @author 皇甫
 */
public class UserDaoImpl {
    public void query(){
        System.out.println("假装查询数据库");
    }
}
