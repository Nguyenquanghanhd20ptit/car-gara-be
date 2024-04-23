package com.example.carmanagement.db.rabbitmq.consumer;

import com.example.carmanagement.commons.data.request.order.StatusOrderRequest;
import com.example.carmanagement.service.orderMomo.OrderMomoComponent;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class StatusOrderRabbitmqConsumer {
    @Autowired
    private OrderMomoComponent orderMomoComponent;
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${rabbitmq.direct-queue.status-order}", durable = "true"),
            exchange = @Exchange(name = "${rabbitmq.direct-exchange-momo}", durable = "true", type = "topic"),
            key = "${rabbitmq.direct-routing.status-order}"
    ))
    @RabbitHandler
    public void onStatusOrderMessage(@Payload StatusOrderRequest request,
                                     @Headers Map<String, Object> headers,
                                     Channel channel) throws IOException {
        if (request != null) {
             orderMomoComponent.updateStatusOrder(request);
        }
        //dung de xac nhan la tin nhan da duoc doc va no se xoa khoi queue
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
    }
}
