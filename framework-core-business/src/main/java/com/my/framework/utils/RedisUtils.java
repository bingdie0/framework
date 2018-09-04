package com.my.framework.utils;

import com.my.framework.exception.BusinessException;
import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-04
 **/
public class RedisUtils {

    private RedisTemplate<String, Object> redisTemplate;

    public RedisUtils setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        return this;
    }

    public boolean set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        return true;
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
      * @description: 指定key的失效时间
      * @param key key
      * @param time 失效时间，单位秒
      * @return: boolean true成功。false失败
      * @author: Mr.WangJie
      * @date: 2018/9/4
      */
    public boolean expire(String key, long time) {
        if (time > 0) {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        } else {
            throw new BusinessException("F_1001", "失效时间必须大于0！");
        }
        return true;
    }
}
