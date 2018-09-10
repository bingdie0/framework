package com.my.framework.redis.autoconfig;

import com.my.framework.redis.prop.RedisProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-10
 **/
@Slf4j
@EnableCaching
@Configuration
@EnableConfigurationProperties(value = RedisProperties.class)
public class RedisAutoConfig extends CachingConfigurerSupport {

    private final RedisProperties redisProperties;

    public RedisAutoConfig(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisProperties.getHostName());
        redisStandaloneConfiguration.setPort(redisProperties.getPort());
        redisStandaloneConfiguration.setPassword(RedisPassword.of(redisProperties.getPassword()));
        redisStandaloneConfiguration.setDatabase(redisProperties.getDbIndex());
        return redisStandaloneConfiguration;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(RedisStandaloneConfiguration redisStandaloneConfiguration) {
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    @Primary
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
        return redisTemplate;
    }

    private void initDomainRedisTemplate(RedisTemplate<Object, Object> redisTemplate, RedisConnectionFactory redisConnectionFactory) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 开启事务
//        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
    }

    /**
     * 缓存异常统一捕获
     */
    @Override
    public CacheErrorHandler errorHandler() {
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
                StringBuilder message = new StringBuilder(50);
                message.append("获取缓存(cache get)出错。cache name=>")
                        .append(cache == null ? "null" : cache.getName())
                        .append(",key=>")
                        .append(key == null ? "null" : key.toString());
                log.error(message.toString(), exception);
                throw exception;
            }

            @Override
            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
                StringBuilder message = new StringBuilder(50);
                message.append("设置缓存(cache put)出错。cache name=>")
                        .append(cache == null ? "null" : cache.getName())
                        .append(",key=>")
                        .append(key == null ? "null" : key.toString());
                log.error(message.toString(), exception);
                throw exception;
            }

            @Override
            public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
                StringBuilder message = new StringBuilder(50);
                message.append("删除缓存(cache evict)出错。cache name=>")
                        .append(cache == null ? "null" : cache.getName())
                        .append(",key=>")
                        .append(key == null ? "null" : key.toString());
                log.error(message.toString(), exception);
                throw exception;
            }

            @Override
            public void handleCacheClearError(RuntimeException exception, Cache cache) {
                StringBuilder message = new StringBuilder(50);
                message.append("清除缓存(cache clear)出错。cache name=>")
                        .append(cache == null ? "null" : cache.getName());
                log.error(message.toString(), exception);
                throw exception;
            }
        };
    }
}
