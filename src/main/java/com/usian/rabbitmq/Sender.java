package com.usian.rabbitmq;

import com.usian.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {App.class})
public class Sender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void testSend() throws InterruptedException {
        String msg="你好师太";
        amqpTemplate.convertAndSend("springboot_exchage","w.c",msg);
        System.out.println("发送消息"+msg);
        Thread.sleep(10000);
    }

}
