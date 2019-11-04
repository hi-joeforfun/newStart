package com.dalaoyang.jdbcUtil;

import com.dalaoyang.annotation.MyTransacation;
import com.dalaoyang.config.MyTransactionManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class MyjdbcTemplate {
    @Autowired
    private MyTransactionManage myTransactionManage;

    public void execute(String sql) throws SQLException {
        Connection connection = myTransactionManage.getConnection(false) ;
        Statement statement = connection.createStatement();
        statement.execute(sql);

    }
}
