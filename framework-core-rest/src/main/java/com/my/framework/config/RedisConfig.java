package com.my.framework.config;

import com.my.framework.utils.RedisUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-04
 **/
@Configuration
public class RedisConfig {

    /*
     * redis集群配置
     */
//    @Bean
//    public RedisClusterConfiguration redisClusterConfiguration(){
//        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
//        //Set<RedisNode> clusterNodes
//        String[] serverArray = clusterNodes.split(",");
//
//        Set<RedisNode> nodes = new HashSet<RedisNode>();
//
//        for(String ipPort:serverArray){
//            String[] ipAndPort = ipPort.split(":");
//            nodes.add(new RedisNode(ipAndPort[0].trim(),Integer.valueOf(ipAndPort[1])));
//        }
//
//        redisClusterConfiguration.setClusterNodes(nodes);
//        redisClusterConfiguration.setMaxRedirects(mmaxRedirectsac);
//
//        return redisClusterConfiguration;
//    }

    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration() {
        return new RedisStandaloneConfiguration("111.231.138.140");
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    public RedisUtils redisUtils(RedisTemplate<String, Object> redisTemplate) {
       return new RedisUtils().setRedisTemplate(redisTemplate);
    }

    private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory redisConnectionFactory) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 开启事务
//        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
    }
}
