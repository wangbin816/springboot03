package com.usian.test.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.usian.util.ConnectionUtil;
import org.springframework.amqp.core.ExchangeTypes;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        String EXCHANGE_NAME="topic_exchange";
        channel.exchangeDeclare(EXCHANGE_NAME, ExchangeTypes.TOPIC);
        String msg="师姨父你好!!!";
        channel.basicPublish(EXCHANGE_NAME,"item.update",null,msg.getBytes());

        channel.close();
        connection.close();
    }
}
