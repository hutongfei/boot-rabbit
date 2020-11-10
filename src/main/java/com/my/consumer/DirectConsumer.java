package com.my.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author hutf
 * @createTime 2020年11月10日 11:18:00
 */
@Component
public class DirectConsumer {

    private final static Logger log = LoggerFactory.getLogger(FanoutConsumer.class);

    @RabbitListener(queuesToDeclare = @Queue("directQueue"))
    public void directListener (String message) {
        System.out.println("direct : ");
        log.info(message);
        System.out.println("");
    }
}
