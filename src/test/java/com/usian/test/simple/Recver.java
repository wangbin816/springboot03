package com.usian.test.simple;

import com.rabbitmq.client.*;
import com.usian.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recver {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        final Channel channel = connection.createChannel();
        String QUEUE_NAME="simple_queue";
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        DefaultConsumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
              String msg=new String(body);
//              int a=6/0;
              System.out.println("recv: "+msg);
//                channel.basicAck(envelope.getDeliveryTag(),false);
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }
}
