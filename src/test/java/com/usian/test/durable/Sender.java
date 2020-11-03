package com.usian.test.durable;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.usian.util.ConnectionUtil;
import org.springframework.amqp.core.ExchangeTypes;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        String EXCHANGE_NAME="durable_exchange";
        channel.exchangeDeclare(EXCHANGE_NAME, ExchangeTypes.TOPIC,true);
        for (int i = 0; i < 50; i++) {
            String msg="师姨父你好......"+i;
            channel.basicPublish(EXCHANGE_NAME,"item.update", MessageProperties.PERSISTENT_TEXT_PLAIN,msg.getBytes());
            System.out.println("send: "+msg);
//            Thread.sleep(500);
        }


        channel.close();
        connection.close();
    }
}
