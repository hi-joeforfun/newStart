package com.dalaoyang.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class MyTransactionManage {

    private ThreadLocal<Connection> connection = new ThreadLocal<>();//用ThreadLocal隔离每个connection 链接 让事物回滚不会影响到其他的语句

    @Autowired
    private DataSource myDataSource;

    public Connection getConnection(boolean satrtTrans) throws SQLException{
        if(connection.get() != null){
            return connection.get();
        }else{
            connection.set(myDataSource.getConnection());
        }

        if(satrtTrans){//开启事物 吧事物默认提交关闭
            System.out.println("开启事物");
            connection.get().setAutoCommit(false);
        }
        return connection.get();//返回数据库链接
    }

    public void clear(){
        connection.remove();
    }
}
