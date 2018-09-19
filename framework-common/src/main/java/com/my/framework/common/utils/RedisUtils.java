package com.my.framework.common.utils;

import com.my.framework.common.exception.BusinessException;
import com.my.framework.common.context.ApplicationContextHelper;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-04
 **/
public class RedisUtils {

    private static RedisTemplate redisTemplate = ApplicationContextHelper.getContext().getBean(RedisTemplate.class);

    /**
      * @description: 将数据放入redis中
      * @param key 键
      * @param value 值
      * @author: Mr.WangJie
      * @date: 2018/9/5
      */
    public static void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
      * @description: 将数据放入Redis中，缓存指定的时间
      * @param key 键
      * @param value 值
      * @param time 缓存时间，单位秒
      * @author: Mr.WangJie
      * @date: 2018/9/5
      */
    public static void set(String key, Object value, long time) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            set(key, value);
        }
    }

    /**
      * @description: 根据key获得值
      * @param key 键
      * @return: java.lang.Object 值
      * @author: Mr.WangJie
      * @date: 2018/9/5
      */
    public static<T> T get(String key, Class<T> targetClass) {
        Object source = redisTemplate.opsForValue().get(key);
        return CopyUtils.copyObject(source, targetClass);
    }

    /**
     * @description: 获取list缓存的内容
     * @param key 键
     * @return: java.util.List<java.lang.Object>
     * @author: Mr.WangJie
     * @date: 2018/9/5
     */
    public static<T> List<T> lGet(String key, Class<T> targetClass) {
        return lGet(key, 0, -1, targetClass);
    }

    /**
      * @description: 获取list缓存的内容
      * @param key 键
      * @param start 开始
      * @param end 结束 0 到 -1 代表所有值
      * @return: java.util.List<java.lang.Object>
      * @author: Mr.WangJie
      * @date: 2018/9/5
      */
    public static<T> List<T> lGet(String key, long start, long end, Class<T> targetClass) {
        List<Object> source = redisTemplate.opsForList().range(key, start, end);
        return CopyUtils.copyList(source, targetClass);
    }

    /**
      * @description: redis中是否包含此key
      * @param key 键
      * @return: boolean true包含，false不包含
      * @author: Mr.WangJie
      * @date: 2018/9/5
      */
    public static boolean hasKey(String key) {
        Boolean hasKey = redisTemplate.hasKey(key);
        return hasKey == null ? false : hasKey;
    }

    /**
      * @description: 删除指定的key和其对应的值
      * @param key 键
      * @author: Mr.WangJie
      * @date: 2018/9/5
      */
    public static void del(String key) {
        redisTemplate.delete(key);
    }

    /**
      * @description: 删除指定的list key和其对应的值
      * @param keys 键list
      * @return: void
      * @author: Mr.WangJie
      * @date: 2018/9/5
      */
    public static void del(List<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
      * @description: 指定key的失效时间
      * @param key key
      * @param time 失效时间，单位秒
      * @return: boolean true成功。false失败
      * @author: Mr.WangJie
      * @date: 2018/9/4
      */
    public static boolean expire(String key, long time) {
        if (time > 0) {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        } else {
            throw new BusinessException("F_1001", "失效时间必须大于0！");
        }
        return true;
    }


}
