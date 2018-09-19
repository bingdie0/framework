package com.my.framework.boot.redis.datasource.mapper;

import com.my.framework.boot.redis.datasource.entity.BaseEntity;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-17
 **/
public interface BaseMapper<T extends BaseEntity> extends Mapper<T>, MySqlMapper<T> {
}
