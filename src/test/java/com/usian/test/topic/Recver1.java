package com.usian.test.topic;

import com.rabbitmq.client.*;
import com.usian.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recver1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        final Channel channel = connection.createChannel();
        String QUEUE_NAME="topic_queue1";
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String EXCHANGE_NAME="topic_exchange";
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"item.insert");


        DefaultConsumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
              String msg=new String(body);
//              int a=6/0;
              System.out.println("recv1: "+msg);
//                channel.basicAck(envelope.getDeliveryTag(),false);
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }
}
