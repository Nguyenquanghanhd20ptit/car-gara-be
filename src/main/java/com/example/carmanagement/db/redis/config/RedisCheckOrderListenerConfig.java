package com.example.carmanagement.db.redis.config;

import com.example.carmanagement.config.RedisConfig;
import com.example.carmanagement.db.redis.listener.StatusOrderRedisListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
@Configuration
public class RedisCheckOrderListenerConfig {

    @Autowired
    private StatusOrderRedisListener statusOrderRedisListener;
    @Autowired
    private RedisTemplate redisTemplate;
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisTemplate.getConnectionFactory());
        ChannelTopic channelTopic = new ChannelTopic("__keyevent@0__:expired");
        container.addMessageListener(statusOrderRedisListener, channelTopic);
        return container;
    }
}
