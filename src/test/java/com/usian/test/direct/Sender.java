package com.usian.test.direct;

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

        String EXCHANGE_NAME="direct_exchange";
        channel.exchangeDeclare(EXCHANGE_NAME, ExchangeTypes.DIRECT);
        String msg="师兄你好!!!";
        channel.basicPublish(EXCHANGE_NAME,"update",null,msg.getBytes());

        channel.close();
        connection.close();
    }
}
