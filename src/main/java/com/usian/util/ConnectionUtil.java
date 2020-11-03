package com.usian.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtil {
    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.17.133");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("1111");
        factory.setVirtualHost("/");
        return factory.newConnection();



    }

}
