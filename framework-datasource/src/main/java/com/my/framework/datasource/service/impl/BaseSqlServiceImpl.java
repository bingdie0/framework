package com.my.framework.datasource.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.my.framework.datasource.entity.BaseEntity;
import com.my.framework.datasource.entity.Operator;
import com.my.framework.datasource.entity.Order;
import com.my.framework.datasource.entity.SearchFilter;
import com.my.framework.datasource.mapper.BaseMapper;
import com.my.framework.datasource.service.BaseSqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-17
 **/
@Service
public class BaseSqlServiceImpl<E extends BaseEntity> implements BaseSqlService {

    @Autowired
    private BaseMapper baseMapper;

    private Class<E> entityClass;

    private BaseSqlServiceImpl() {
        Type genType = getClass().getGenericSuperclass();

        if (genType instanceof ParameterizedType) {
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            this.entityClass = (Class<E>) params[0];

        }
    }


    @Override
    public <T> List<T> selectList(List<SearchFilter> filters, List<Order> orders) {
        Example example = getExample(filters, orders);
        List<T> list = baseMapper.selectByExample(example);
        return list;
    }

    @Override
    public <T> Page<T> queryByPage(Page<T> page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        System.out.println(page.getClass());
        return page;
    }

    private Example getExample(List<SearchFilter> searchFilters, List<Order> orders) {
        Example example = new Example(entityClass);
        if (CollectionUtils.isEmpty(orders)) {
            orders = new ArrayList<>();
        }

        if (!CollectionUtils.isEmpty(searchFilters)) {
            setSearchFilters(example, searchFilters);
        }

        setOrders(example, orders);
        return example;
    }


    /**
     * 设置过滤条件
     *
     * @param example example
     * @param searchFilters 过滤列表
     */
    private void setSearchFilters(Example example, List<SearchFilter> searchFilters) {
        if (example == null) {
            throw new IllegalArgumentException("setSearchFilters: example must not be empty");
        }
        Example.Criteria createCriteria = example.createCriteria();
        for (SearchFilter searchFilter : searchFilters) {
            String property = searchFilter.getProperty();
            Object value = searchFilter.getValue();
            Operator operator = searchFilter.getOperator();
            switch (operator) {
                case EQ:
                    if (value == null) {
                        createCriteria.andIsNull(property);
                        break;
                    }
                    createCriteria.andEqualTo(property, value);
                    break;
                case NE:
                    if (value == null) {
                        createCriteria.andIsNull(property);
                        break;
                    }
                    createCriteria.andNotEqualTo(property, value);
                    break;
                case GT:
                    if (value == null) {
                        createCriteria.andIsNull(property);
                        break;
                    }
                    createCriteria.andGreaterThan(property, value);
                    break;
                case GE:
                    if (value == null) {
                        createCriteria.andIsNull(property);
                        break;
                    }
                    createCriteria.andGreaterThanOrEqualTo(property, value);
                    break;
                case LT:
                    if (value == null) {
                        createCriteria.andIsNull(property);
                        break;
                    }
                    createCriteria.andLessThan(property, value);
                    break;
                case LE:
                    if (value == null) {
                        createCriteria.andIsNull(property);
                        break;
                    }
                    createCriteria.andLessThanOrEqualTo(property, value);
                    break;
                case LIKE:
                    if (value == null) {
                        createCriteria.andIsNull(property);
                        break;
                    }
                    createCriteria.andLike(property, (String) value);
                    break;
                case NOTLIKE:
                    if (value == null) {
                        createCriteria.andIsNull(property);
                        break;
                    }
                    createCriteria.andNotLike(property, (String) value);
                    break;
                case IN:
                    if (value instanceof List) {
                        List list = (List) value;
                        if (CollectionUtils.isEmpty(list)) {
                            createCriteria.andIsNull(property);
                            break;
                        }
                    }
                    createCriteria.andIn(property, (Iterable) value);
                    break;
                case NOTIN:
                    if (value instanceof List) {
                        List list = (List) value;
                        if (CollectionUtils.isEmpty(list)) {
                            createCriteria.andIsNull(property);
                            break;
                        }
                    }
                    createCriteria.andNotIn(property, (Iterable) value);
                    break;
                case ISNULL:
                    createCriteria.andIsNull(property);
                    break;
                case ISNOTNULL:
                    createCriteria.andIsNotNull(property);
                    break;
                case BT:
                    if (value instanceof List) {
                        List list = (List) value;
                        createCriteria.andBetween(property, list.get(0), list.get(1));
                    }
                    break;
                case NOTBT:
                    if (value instanceof List) {
                        List list = (List) value;
                        createCriteria.andNotBetween(property, list.get(0), list.get(1));
                    }
                    break;
                case CONDITION:
                    if (value == null) {
                        createCriteria.andCondition(property);
                    } else {
                        createCriteria.andCondition(property, value);
                    }
                default:
                    break;
            }
        }
    }

    /**
     * 设置排序
     *
     * @param example example
     * @param orders  排序列表
     */
    private void setOrders(Example example, List<Order> orders) {
        if (example == null) {
            throw new IllegalArgumentException("setOrders: example must not be empty");
        }
        if (orders == null) {
            return;
        }
        Example.OrderBy ob = null;
        for (Order order : orders) {
            if (ob == null) {
                ob = example.orderBy(order.getProperty());
            } else {
                ob.orderBy(order.getProperty());
            }

            switch (order.getOrderType()) {
                case ASC:
                    ob.asc();
                    break;
                case DESC:
                    ob.desc();
                    break;
                default:
                    break;
            }
        }
    }

}
