package com.usian.test.durable;

import com.rabbitmq.client.*;
import com.usian.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recver1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        final Channel channel = connection.createChannel();
        String QUEUE_NAME="durable_queue1";
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        String EXCHANGE_NAME="durable_exchange";
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"item.*");


        DefaultConsumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
              String msg=new String(body);
//              int a=6/0;
              System.out.println("recv1: "+msg);
//                channel.basicAck(envelope.getDeliveryTag(),false);
                channel.basicAck(envelope.getDeliveryTag(),false);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }
}
