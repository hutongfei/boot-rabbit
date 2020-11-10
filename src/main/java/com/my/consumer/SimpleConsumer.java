package com.my.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author hutf
 * @createTime 2020年11月10日 09:44:00
 */
@Component
public class SimpleConsumer {

    private final static Logger log = LoggerFactory.getLogger(SimpleConsumer.class);

    @RabbitListener(queuesToDeclare = @Queue("simpleQueue"))
    public void listenerSimpleConsumer(String msg) {
        log.info("");
        log.info(msg);
        log.info("");

    }

}
