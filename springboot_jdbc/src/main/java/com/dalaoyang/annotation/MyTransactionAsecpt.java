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

    @Around("@annotation(MyTransacation)")//捕获MyTransacation注解 增加切面
    public Object doTransactional(ProceedingJoinPoint proceedingJoinPoint)throws SQLException{

        Connection connection = null;
        System.out.println("目标前置");
        try {
            connection = myTransactionManage.getConnection(true);//增加事物管理器，参数 true开启事物
            proceedingJoinPoint.proceed();//执行代理里面真正的方法内容
            connection.commit();//事物提交
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
