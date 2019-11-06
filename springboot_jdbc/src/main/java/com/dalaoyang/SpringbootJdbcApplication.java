package com.dalaoyang;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.sql.DataSource;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringbootJdbcApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SpringbootJdbcApplication.class, args);

    }



    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean(name = "myDataSource")
    @Qualifier("myDataSource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }
}
