package com.my.product;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author hutf
 * @createTime 2020年11月10日 10:28:00
 */
@Component
public class DirectProduct {

    @Autowired
    private RabbitTemplate rabbitTemplate;

//    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void  directProduct() {
        rabbitTemplate.convertAndSend("directExchange", "directKey"," directProduct : this is my message " + UUID.randomUUID().toString());
    }
}
