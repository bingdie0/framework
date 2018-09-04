package com.my.framework.controller;

import com.my.framework.request.TestResponse;
import com.my.framework.utils.RedisUtils;
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
    private RedisUtils redisUtils;

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
        redisUtils.set("hello", "world");
        testResponse.setName((String) redisUtils.get("hello"));
        return testResponse;
    }
}
