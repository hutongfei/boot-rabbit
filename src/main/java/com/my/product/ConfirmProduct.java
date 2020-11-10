package com.my.product;

import com.my.consumer.ConfirmHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.UUID;

/**
 * @author hutf
 * @createTime 2020年11月10日 14:21:00
 */
@Component
public class ConfirmProduct {

    private final static Logger log = LoggerFactory.getLogger(ConfirmProduct.class);

    @Autowired
    @Resource(name = "confirmRabbitTemplate")
    private RabbitTemplate rabbitTemplate;

    @Scheduled(initialDelay = 1000,fixedDelay = 3000)
    public void  sendMessage() {
                CorrelationData cId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("confirmExchange", "confirmKey"," sendMessage: "+ UUID.randomUUID().toString(),cId);
    }
}
