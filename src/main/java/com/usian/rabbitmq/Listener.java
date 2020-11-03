package com.usian.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "springboot_queue",durable = "true"),
            exchange = @Exchange(value = "springboot_exchage",type = ExchangeTypes.TOPIC),
            key = {"*.*"}
    ))
    public void listen(String msg, Channel channel,Mess) {
        System.out.println("接收到消息  "+msg);
    }


}
