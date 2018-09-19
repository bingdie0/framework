package com.my.framework.boot.redis.datasource.entity;

import com.my.framework.boot.redis.datasource.enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-17
 **/
@Data
@AllArgsConstructor
public class Order {
    /**
     * 属性
     */
    private String property = "createTime";

    /**
     * 方向
     */
    private OrderType orderType = OrderType.DESC;


    /**
     * 返回递增排序
     *
     * @param property 属性
     * @return 递增排序
     */
    public static Order asc(String property) {
        return new Order(property, OrderType.ASC);
    }

    /**
     * 返回递减排序
     *
     * @param property 属性
     * @return 递减排序
     */
    public static Order desc(String property) {
        return new Order(property, OrderType.DESC);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Order other = (Order) obj;
        return new EqualsBuilder().append(getProperty(), other.getProperty()).append(getOrderType(), other.getOrderType()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getProperty()).append(getOrderType()).toHashCode();
    }
}
