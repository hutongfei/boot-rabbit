package com.my.product;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author hutf
 * @createTime 2020年11月10日 09:51:00
 */
@Component
public class SimpleProduct {

    @Autowired
    private RabbitTemplate rabbitTemplate;

//    @Scheduled(fixedDelay = 20000, initialDelay = 20000)
    public void sendMessage() {
        rabbitTemplate.convertAndSend("simpleQueue", "this is message body");
    }
}
