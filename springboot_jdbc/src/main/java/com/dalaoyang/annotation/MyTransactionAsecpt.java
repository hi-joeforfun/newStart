package com.dalaoyang.annotation;

import com.dalaoyang.config.MyTransactionManage;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
@Aspect
public class MyTransactionAsecpt {

    @Autowired
    MyTransactionManage myTransactionManage;

    @Around("@annotation(MyTransacation)")
    public Object doTransactional(ProceedingJoinPoint proceedingJoinPoint)throws SQLException{

        Connection connection = null;
        System.out.println("目标前置");
        try {
            connection = myTransactionManage.getConnection(true);
            proceedingJoinPoint.proceed();
            connection.commit();
        }catch (Throwable throwable){
            throwable.printStackTrace();
            if(connection!=null){
                System.out.println("事物回滚");
                connection.rollback();//失败就回滚
            }
        }finally {
            connection.close();
            myTransactionManage.clear();
        }
        System.out.println("目标后置");
        return  null;
    }
}
