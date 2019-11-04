package com.dalaoyang.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class MyTransactionManage {

    private ThreadLocal<Connection> connection;

    @Autowired
    private DataSource myDataSource;

    public Connection getConnection(boolean satrtTrans) throws SQLException{
        if(connection.get()!=null){
            return connection.get();
        }else{
            connection.set(myDataSource.getConnection());
        }

        if(satrtTrans){
            System.out.println("开启事物");
            connection.get().setAutoCommit(false);
        }
        return connection.get();
    }

    public void clear(){
        connection.remove();
    }
}
