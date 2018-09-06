package com.my.framework.controller;

import com.my.framework.properties.ErrorProperties;
import com.my.framework.properties.ResolveProperties;
import com.my.framework.request.TestResponse;
import com.my.framework.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.StandardServletEnvironment;

import java.util.Map;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-03
 **/
@RestController
public class TestController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private ResolveProperties resolveProperties;

    /**
     * @description:
     * @return: java.lang.String
     * @author: Mr.WangJie
     * @date: 2018/9/3
     */
    @GetMapping("/test")
    public TestResponse test() {
        TestResponse testResponse = new TestResponse();
        testResponse.setName("Bob");
        redisUtils.set("hello", "Bob");
        testResponse.setName(redisUtils.get("hello", String.class));
        return testResponse;
    }

    @GetMapping("/config")
    public String config() {
        return resolveProperties.getValue("F_1002", "error.properties");
    }
}
