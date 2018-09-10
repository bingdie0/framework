package com.my.framework.redis.controller;

import com.my.framework.redis.properties.ResolveProperties;
import com.my.framework.redis.request.TestResponse;
import com.my.framework.redis.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-03
 **/
@RestController
public class TestController {

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
        RedisUtils.set("hello", "CC");
        testResponse.setName(RedisUtils.get("hello", String.class));
        return testResponse;
    }

    @GetMapping("/config")
    public String config() {
        return resolveProperties.getValue("F_1002", "error.properties");
    }
}
