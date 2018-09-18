package com.my.framework.datasource.db;

import com.github.pagehelper.Page;

import java.util.Map;
import java.util.Objects;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-17
 **/
public class SQLHelp {

    /**
     * map传参每次都将currentPage重置，先判断map再判断context
     *
     * @param parameterObject 参数
     * @return:
     * @author: Mr.WangJie
     */
    public static Page getPage(Map parameterObject) {
        Page page = null;
        if (Objects.nonNull(parameterObject)) {
            //没有参数直接返回
            if (parameterObject.isEmpty()) {
                return null;
            }
            // 当dao中的参数为一个Map<String, Object>,且query为Map中的对象
            if (parameterObject.containsKey("page")) {
                page = convertParameter(parameterObject.get("page"), page);
            } else {
                // 当dao为参数列表，且query为第一个查询参数
                page = convertParameter(parameterObject.get("param1"), page);
            }
        }
        return page;
    }

    /**
     * 对参数进行转换和检查
     *
     * @param parameterObject 参数对象
     * @param page 参数
     * @return:
     * @author: Mr.WangJie
     */
    public static Page convertParameter(Object parameterObject, Page page) {
        if (parameterObject instanceof Page) {
            page = (Page) parameterObject;
        }
        return page;
    }

    public static void initPagination(Page page) {

    }

    public static String generateOrderSql(String sql, FPage fPage, Dialect dialect) {
        return dialect.getOrderString(sql, fPage.getOrderColumns(), fPage.getOrderType());
    }

    /**
     * 根据数据库方言生成特定的分页sql
     *
     * @param sql 原始sql
     * @param page 分页参数
     * @param dialect 数据库方言
     * @return: 分页sql
     * @author: Mr.WangJie
     */
    public static String generatePageSql(String sql, Page page, Dialect dialect) {
        int pageSize = page.getPageSize();
        int index = (page.getPageNum() - 1) * pageSize;
        int start = index < 0 ? 0 : index;
        return dialect.getLimitString(sql, start, pageSize);
    }
}
