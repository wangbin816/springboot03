package com.usian.test.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.usian.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        String QUEUE_NAME="work_queue";
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        for (int i = 0; i < 50; i++) {
            String msg="师姐你好!!!"+i;
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            System.out.println("send: "+msg);
            Thread.sleep(i*2);
        }
        channel.close();
        connection.close();
    }
}
