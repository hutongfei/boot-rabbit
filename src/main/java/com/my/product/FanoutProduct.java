package com.my.product;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author hutf
 * @createTime 2020年11月10日 10:28:00
 */
@Component
public class FanoutProduct {

    @Autowired
    private RabbitTemplate rabbitTemplate;

//    @Scheduled(fixedDelay = 20000, initialDelay = 20000)
    public void  fanoutProduct() {
        // 广播模式无需指定路由键
        rabbitTemplate.convertAndSend("fanoutExchange", ""," fanoutExchange: this is my message ");
    }
}
