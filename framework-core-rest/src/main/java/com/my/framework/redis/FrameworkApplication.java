package com.my.framework.redis;

import com.my.framework.context.ApplicationContextHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 * @author: Mr.WangJie
 * @create: 2018-09-03
 **/
@SpringBootApplication
public class FrameworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameworkApplication.class, args);
    }

    @Bean
    public ApplicationContextHelper applicationContextHelper() {
        return new ApplicationContextHelper();
    }

}
