
package com.my.framework.config;

import org.springframework.context.annotation.Configuration;


/**
 * @author: Mr.WangJie
 * @date: 2018-09-04
 **/

@Configuration
public class RedisConfig {


//    @Bean
//    public RedisUtils redisUtils(RedisTemplate<String, Object> redisTemplate) {
//       return new RedisUtils().setRedisTemplate(redisTemplate);
//    }

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
}

