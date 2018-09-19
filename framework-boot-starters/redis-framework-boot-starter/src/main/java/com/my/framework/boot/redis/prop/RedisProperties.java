package com.my.framework.boot.redis.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-10
 **/
@Data
@Validated
@ConfigurationProperties(prefix = "my.redis")
public class RedisProperties {

    @Min(value = 0, message = "redis maxTotal不能小于0")
    private int maxTotal = 100;

    @Min(value = 0, message = "redis maxIdle不能小于0")
    private int maxIdle = 30;

    @Min(value = 0, message = "redis minIdle不能小于0")
    private int minIle = 5;

    @NotEmpty(message = "redis hostname不能为空")
    private String hostName = "111.231.138.140";

    @Min(value = 0, message = "redis port不能小于0")
    private int port = 6379;

    private String password;

    @Min(value = 0, message = "redis dbIndex不能小于0")
    private int dbIndex = 0;

    @Min(value = 0, message = "redis timeout不能小于0")
    private int timeout = 1000;

    @NotNull(message = "redis缓存是否添加前缀不能为空")
    private Boolean usePrefix = Boolean.TRUE;

    @Min(value = 0, message = "默认过期时间不能小于0")
    private long defaultExpiration = 3600;

    private Map<String, Long> expires;
}
