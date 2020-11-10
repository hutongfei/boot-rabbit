package com.my.consumer;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @author hutf
 * @createTime 2020年11月10日 14:39:00
 */
@Component
public class ConfirmConsumer {

    private final static Logger log = LoggerFactory.getLogger(ConfirmConsumer.class);

    @RabbitListener(queuesToDeclare = @Queue("confirmQueue"))
    public void ConfirmListener(String msg, Channel channel, Message message) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            log.info("  消息为： " +msg);
            // 确认这条消息
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            e.printStackTrace();
            // 丢弃这条消息
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }
}
