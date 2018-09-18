package com.my.framework.controller;

import com.my.framework.properties.ResolveProperties;
import com.my.framework.request.TestRequest;
import com.my.framework.response.TestResponse;
import com.my.framework.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @PostMapping("/test")
    public String test(@RequestBody TestRequest request) {

        return request.getName();
    }

    @GetMapping("/config")
    public String config() {
        return resolveProperties.getValue("F_1002", "error.properties");
    }
}