package com.my.framework.datasource.service;

import com.github.pagehelper.Page;
import com.my.framework.datasource.entity.Order;
import com.my.framework.datasource.entity.SearchFilter;

import java.util.List;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-17
 **/
public interface BaseSqlService<T> {

    /**
     * 根据条件查询排序
     *
     * @param filters 过滤条件
     * @param orders  排序列表
     * @return
     */
    List<T> selectList(List<SearchFilter> filters, List<Order> orders);


    Page<T> queryByPage(Page<T> page);
}
