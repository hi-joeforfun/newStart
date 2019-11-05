package com.dalaoyang.service;

import com.dalaoyang.annotation.MyTransacation;
import com.dalaoyang.jdbcUtil.MyjdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserService {

    @Autowired
    MyjdbcTemplate myjdbcTemplate;

    @MyTransacation//自定义事物注解
    public  void fun()throws SQLException{
        myjdbcTemplate.execute("INSERT INTO USER (name,password,date) VALUES ('dalaoyang','123',now())");
        int i=1/0;//跑出异常回滚
    }
}
