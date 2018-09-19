package com.my.framework.boot.redis.datasource.db;

import com.my.framework.boot.redis.datasource.enums.OrderType;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-17
 **/
public class MySqlDialect implements Dialect {

    @Override
    public String getOrderString(String sql, String orderColumns, OrderType orderType) {
        if (StringUtils.isBlank(sql)) {
            return sql;
        }

        StringBuilder stringBuilder = new StringBuilder(sql);
        String type;
        if (Objects.isNull(orderType) || orderType.equals(OrderType.DESC)) {
            type = "desc";
        } else {
            type = "asc";
        }
        stringBuilder.append(" order by ").append(orderColumns).append(" ").append(type);
        return stringBuilder.toString();
    }

    @Override
    public String getLimitString(String sql, int offset, int limit) {
        StringBuilder stringBuilder = new StringBuilder(sql);
        stringBuilder.append(" limit ");
        if (offset > 0) {
            stringBuilder.append(offset).append(",").append(limit);
        } else {
            stringBuilder.append(limit);
        }
        return stringBuilder.toString();
    }
}
