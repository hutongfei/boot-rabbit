package com.my.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author hutf
 * @createTime 2020年11月10日 14:19:00
 */
//@Component
public class ConfirmHandler implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private final static Logger log = LoggerFactory.getLogger(ConfirmHandler.class);

    /**
     * @param correlationData   消息发送唯一ID
     * @param ack   是否成功
     * @param cause 原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info(correlationData + "消息发送成功");
            return;
        }

        log.info("消息发送失败,错误原因 {}",cause);
    }

    /**
     *
     * @param message 消息对象
     * @param replyCode 错误代码
     * @param replyText 错误信息
     * @param exchange  交换机名称
     * @param routingKey    路由Key
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info(" returnedMessage 执行啦 ，return 信息为 " + replyText);
    }
}
