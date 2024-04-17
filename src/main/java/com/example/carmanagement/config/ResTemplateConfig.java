package com.example.carmanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class ResTemplateConfig {
    @Autowired
    private ResourceProperties resourceProperties;
    @Bean
    public RestTemplate restTemplateUnit(){
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofMillis( resourceProperties.getTimeout()))
                .setReadTimeout(Duration.ofMillis(resourceProperties.getTimeout())).build();
    }
}
