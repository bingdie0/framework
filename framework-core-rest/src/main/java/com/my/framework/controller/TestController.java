package com.my.framework.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-03
 **/
@RestController
public class TestController {

    /**
     * @description:
     * @return: java.lang.String
     * @author: Mr.WangJie
     * @date: 2018/9/3
     */
    @GetMapping("/test")
    public String test() {
        return "hello world";
    }
}
