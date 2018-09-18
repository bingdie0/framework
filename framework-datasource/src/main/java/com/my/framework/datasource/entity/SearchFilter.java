package com.my.framework.datasource.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-17
 **/
@Data
@AllArgsConstructor
public class SearchFilter implements Serializable {
    /**
     * 属性
     */
    private String property;

    /**
     * 运算符
     */
    private Operator operator;

    /**
     * 值
     */
    private Object value;

    /**
     * 是否忽略大小写
     */
    private Boolean ignoreCase = false;


    /**
     * 初始化创建一个filter
     *
     * @param property 属性
     * @param operator 操作符
     * @param value    值
     */
    public SearchFilter(String property, Operator operator, Object value) {
        super();
        this.property = property;
        this.operator = operator;
        this.value = value;
    }

    /**
     * 返回等于筛选
     *
     * @param property 属性
     * @param value    值
     * @return 等于筛选
     */
    public static SearchFilter eq(String property, Object value) {
        return new SearchFilter(property, Operator.EQ, value);
    }

    /**
     * 返回等于筛选
     *
     * @param property   属性
     * @param value      值
     * @param ignoreCase 忽略大小写
     * @return 等于筛选
     */
    public static SearchFilter eq(String property, Object value, boolean ignoreCase) {
        return new SearchFilter(property, Operator.EQ, value, ignoreCase);
    }

    /**
     * 返回不等于筛选
     *
     * @param property 属性
     * @param value    值
     * @return 不等于筛选
     */
    public static SearchFilter ne(String property, Object value) {
        return new SearchFilter(property, Operator.NE, value);
    }

    /**
     * 返回不等于筛选
     *
     * @param property   属性
     * @param value      值
     * @param ignoreCase 忽略大小写
     * @return 不等于筛选
     */
    public static SearchFilter ne(String property, Object value, boolean ignoreCase) {
        return new SearchFilter(property, Operator.NE, value, ignoreCase);
    }

    /**
     * 返回大于筛选
     *
     * @param property 属性
     * @param value    值
     * @return 大于筛选
     */
    public static SearchFilter gt(String property, Object value) {
        return new SearchFilter(property, Operator.GT, value);
    }

    /**
     * 返回小于筛选
     *
     * @param property 属性
     * @param value    值
     * @return 小于筛选
     */
    public static SearchFilter lt(String property, Object value) {
        return new SearchFilter(property, Operator.LT, value);
    }

    /**
     * 返回大于等于筛选
     *
     * @param property 属性
     * @param value    值
     * @return 大于等于筛选
     */
    public static SearchFilter ge(String property, Object value) {
        return new SearchFilter(property, Operator.GE, value);
    }

    /**
     * 返回小于等于筛选
     *
     * @param property 属性
     * @param value    值
     * @return 小于等于筛选
     */
    public static SearchFilter le(String property, Object value) {
        return new SearchFilter(property, Operator.LE, value);
    }

    /**
     * 返回相似筛选
     *
     * @param property 属性
     * @param value    值
     * @return 相似筛选
     */
    public static SearchFilter like(String property, Object value) {
        return new SearchFilter(property, Operator.LIKE, value);
    }

    /**
     * @param property 属性
     * @param value    值
     * @Title: notLike
     * @Description: 返回
     * @return: 不相似筛选
     */
    public static SearchFilter notLike(String property, Object value) {
        return new SearchFilter(property, Operator.NOTLIKE, value);
    }

    /**
     * 返回包含筛选
     *
     * @param property 属性
     * @param value    值
     * @return 包含筛选
     */
    public static SearchFilter in(String property, Object value) {
        return new SearchFilter(property, Operator.IN, value);
    }

    /**
     * 返回为Null筛选
     *
     * @param property 属性
     * @return 为Null筛选
     */
    public static SearchFilter isNull(String property) {
        return new SearchFilter(property, Operator.ISNULL, null);
    }

    /**
     * 返回不为Null筛选
     *
     * @param property 属性
     * @return 不为Null筛选
     */
    public static SearchFilter isNotNull(String property) {
        return new SearchFilter(property, Operator.ISNOTNULL, null);
    }

    /**
     * @param property
     * @param value
     * @return
     * @Title: notIn
     * @Description: 返回不包含的筛选
     * @return: SearchFilter
     */
    public static SearchFilter notIn(String property, Object value) {
        return new SearchFilter(property, Operator.NOTIN, value);
    }

    /**
     * @param property
     * @param value
     * @return
     * @Title: between
     * @Description: 返回在区间内的查询条件
     * @return: SearchFilter
     */
    public static SearchFilter between(String property, List<Object> value) {
        return new SearchFilter(property, Operator.BT, value);
    }

    /**
     * @param property
     * @param value
     * @return
     * @Title: notBetween
     * @Description: 返回不在区间内的查询条件
     * @return: SearchFilter
     */
    public static SearchFilter notBetween(String property, List<Object> value) {
        return new SearchFilter(property, Operator.NOTBT, value);
    }

    /**
     * @param property
     * @param value
     * @return
     * @Title: condition
     * @Description: 返回手写条件的查询条件
     * @return: SearchFilter
     */
    public static SearchFilter condition(String property, String value) {
        return new SearchFilter(property, Operator.CONDITION, value);
    }
}
