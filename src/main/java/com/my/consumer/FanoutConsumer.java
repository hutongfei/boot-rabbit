package com.my.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author hutf
 * @createTime 2020年11月10日 10:37:00
 */
@Component
public class FanoutConsumer {

    private final static Logger log = LoggerFactory.getLogger(FanoutConsumer.class);

    @RabbitListener(queuesToDeclare = @Queue("firstFanoutQueue"))
    public void fanoutConsumerFirst(String msg) {
        log.info("first  ");
        log.info(msg);
        log.info("");
    }

    @RabbitListener(queuesToDeclare = @Queue("secondFanoutQueue"))
    public void fanoutConsumerSecond(String msg) {
        log.info("second  ");
        log.info(msg);
        log.info("");
    }

}
