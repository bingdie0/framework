package com.my.framework.boot.redis.datasource.db;

import com.github.pagehelper.Page;
import com.my.framework.boot.redis.datasource.enums.OrderType;
import lombok.Data;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-17
 **/
@Data
public class FPage extends Page {

    /**
     * 排序列
     */
    private String orderColumns;

    /**
     * 排序方式
     */
    private OrderType orderType;
}
