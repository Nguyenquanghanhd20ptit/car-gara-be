package com.example.carmanagement.db.rabbitmq.config;

import com.example.carmanagement.config.ResourceProperties;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMqConfig {
    @Autowired
    private ResourceProperties resourceProperties;
    @Bean
    Queue queue() {
        return new Queue(resourceProperties.getRabbitmqDirectQueueStatusOrder(), true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(resourceProperties.getRabbitmqDirectExchange());
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(resourceProperties.getRabbitmqDirectRoutingStatusOrder());
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
