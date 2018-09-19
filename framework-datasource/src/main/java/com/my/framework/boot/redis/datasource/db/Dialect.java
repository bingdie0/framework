package com.my.framework.boot.redis.datasource.db;

import com.my.framework.boot.redis.datasource.enums.OrderType;

/**
 * 数据库方言接口
 *
 * @author: Mr.WangJie
 * @date: 2018-09-17
 **/
public interface Dialect {

    /**
     * 将sql转换为排序sql
     *
     * @param sql 未排序sql
     * @param orderColumns 排序列
     * @param orderType 排序类型
     * @return:
     * @author: Mr.WangJie
     */
    String getOrderString(String sql, String orderColumns, OrderType orderType);

    /**
     * 将sql转换为分页sql
     *
     * @param sql 原始语句
     * @param offset 开始位置
     * @param limit 每页显示多少条，偏移量
     * @return: 分页sql
     * @author: Mr.WangJie
     */
    String getLimitString(String sql, int offset, int limit);
}
