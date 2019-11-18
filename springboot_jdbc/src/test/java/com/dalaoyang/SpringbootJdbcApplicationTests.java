package com.dalaoyang;

import com.dalaoyang.annotation.MyTransacation;
import com.dalaoyang.jdbcUtil.MyjdbcTemplate;
import com.dalaoyang.proxy.DynamicProxy;
import com.dalaoyang.service.imp.LuluseImp;
import com.dalaoyang.service.UserService;
import com.dalaoyang.service.Luluse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJdbcApplicationTests {

    @Autowired
    private MyjdbcTemplate myjdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource myDataSource;

    @Autowired
    private UserService userService;

    @Before
    public void getBean(){
        // 获取配置的数据源
//        DataSource dataSource = applicationContext.getBean(DataSource.class);

    }


    @Test
    @MyTransacation
    public void contextLoads() throws SQLException{
        Connection connection = null ;
//        String sql = "INSERT INTO USER (name,password,date) VALUES ('dalaoyang','123',now())";
//        int rows= jdbcTemplate.update(sql);
        try {
            connection = jdbcTemplate.getDataSource().getConnection();
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.execute("delete FROM  user where id=2");
            statement.execute("delete FROM  user2 where id=1");
            connection.commit();
        }catch (SQLException e){
            e.printStackTrace();
            if (connection != null){
                connection.rollback();
            }
        }
    }

    @Test
    public void test()throws SQLException {
        userService.fun();
    }

    @Test
    public void testDynamicProxy(){
//        Method method = new Method();
        Luluse zhangsan = new LuluseImp();
        Luluse luluse = DynamicProxy.getProxy(zhangsan,zhangsan.getClass());
        luluse.luluse("张三");
    }

    @Test
    public void action()throws SQLException {
        System.out.println("joe"); System.out.println(" 我是joe 卧槽");
    }
}
