package com.my.framework.boot.redis.datasource.entity;

import lombok.AllArgsConstructor;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-17
 **/
@AllArgsConstructor
public enum  Operator {
    /**
     * 等于
     */
    EQ("="),
    /**
     * 不等于
     */
    NE("<>"),
    /**
     * 大于
     */
    GT(">"),
    /**
     * 大于等于
     */
    GE(">="),
    /**
     * 小于
     */
    LT("<"),
    /**
     * 小于等于
     */
    LE("<="),
    /**
     * 相似
     */
    LIKE("like"),
    /**
     * 不相似
     */
    NOTLIKE("not like"),
    /**
     * 在...里面
     */
    IN("in"),
    /**
     * 不在....里面
     */
    NOTIN("not in"),
    /**
     * 为空
     */
    ISNULL("is null"),
    /**
     * 不为空
     */
    ISNOTNULL("is not null"),
    /**
     * 在两个值之间，集合,大小为2
     */
    BT("between"),
    /**
     * 不在两个值之间，集合,大小为2
     */
    NOTBT("not between"),
    /**
     * 条件查询
     */
    CONDITION("condition"),
    /**
     * 暂时没用
     */
    ENUM("enum");

    private String name;
}
