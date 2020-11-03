package com.usian.test.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.usian.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        String EXCHANGE_NAME="fanout_exchange";
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        String msg="师弟你好!!!";
        channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes());

        channel.close();
        connection.close();
    }
}
