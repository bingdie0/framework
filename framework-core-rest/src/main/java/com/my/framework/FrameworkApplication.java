package com.my.framework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author: Mr.WangJie
 * @create: 2018-09-03
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.my.framework.dao")
public class FrameworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameworkApplication.class, args);
    }

}
