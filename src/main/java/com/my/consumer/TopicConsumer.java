package com.my.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import sun.security.util.AuthResources_it;

/**
 * @author hutf
 * @createTime 2020年11月10日 11:18:00
 */
@Component
public class TopicConsumer {

    private final static Logger log = LoggerFactory.getLogger(TopicConsumer.class);

    @RabbitListener(queuesToDeclare = @Queue("topicQueue"))
    public void topicListener (String message) {
        System.out.println("主题 1 : ");
        log.info(message);
        System.out.println("");
    }


    @RabbitListener(queuesToDeclare = @Queue("topicQueueSecond"))
    public void secondTopicListener(String message) {
        System.out.println(" 主题 2   ");
        log.info(message);
        System.out.println("   ");
    }
}
