package com.my.framework.boot.redis.datasource.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-17
 **/
@Data
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 3916284913790466507L;

    /**
     * 数据库主键id ： 使用jdbc是根据数据库的规则，可以实现save的时候返回id
     */
    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    /**
     * 创建日期
     */
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    /**
     * 更新时间日期
     */
    @Column(name = "update_time", nullable = false)
    private Date updateTime;

    @Column(name = "disable")
    private Integer disable;
}
