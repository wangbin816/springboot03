package com.usian.test.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.usian.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        String QUEUE_NAME="simple_queue";
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String msg="师姐你好!!!";
        channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
        System.out.println("send: "+msg);
        channel.close();
        connection.close();
    }
}
