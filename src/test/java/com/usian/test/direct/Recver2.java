package com.usian.test.direct;

import com.rabbitmq.client.*;
import com.usian.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recver2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        final Channel channel = connection.createChannel();
        String QUEUE_NAME="direct_queue2";
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String EXCHANGE_NAME="direct_exchange";
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"insert");



        DefaultConsumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
              String msg=new String(body);
//              int a=6/0;
              System.out.println("recv2: "+msg);
//                channel.basicAck(envelope.getDeliveryTag(),false);
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }
}
