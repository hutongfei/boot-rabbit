//package com.my.service;
//
//import com.my.config.RabbitMqConfig;
//import com.rabbitmq.client.Channel;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.core.ExchangeTypes;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.Exchange;
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.annotation.QueueBinding;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.concurrent.TimeoutException;
//
///**
// * @author hutf
// * @createTime 2020年11月09日 15:55:00
// */
//@Component
//public class RabbitConsumer implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback  {
//
//    private static final  Logger log = LoggerFactory.getLogger(RabbitConsumer.class);
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    public RabbitConsumer(RabbitTemplate rabbitTemplate) {
//        rabbitTemplate.setConfirmCallback(this);
//        rabbitTemplate.setReturnCallback(this);
//    }
//
//    /**
//     * 确认消息是否发送到exchange
//     */
//    @Override
//    public void confirm(CorrelationData correlationData, boolean b, String cause) {
//        log.info("消息唯一标识id:{}", correlationData);
//        log.info("消息确认结果!");
//        log.error("消息失败原因,cause:{}", cause);
//    }
//
//    /**
//     * 消息发送异常时，调用
//     */
//    @Override
//    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//        log.info("消息发送失败id:{}", message.getMessageProperties().getCorrelationId());
//        log.info("消息主体 message : ", message);
//        log.info("消息主体 message : ", replyCode);
//        log.info("描述：" + replyText);
//        log.info("消息使用的交换器 exchange : ", exchange);
//        log.info("消息使用的路由键 routing : ", routingKey);
//    }
//
//
//    //如果不存在，自动创建队列和交换器并且绑定
//    @RabbitListener(bindings = {
//            @QueueBinding(value = @Queue(value = RabbitMqConfig.DIRECT_QUEUE, durable = "true"),
//                    exchange = @Exchange(value = RabbitMqConfig.TOPIC_EXCHANGE, type = ExchangeTypes.TOPIC),
//                    key = RabbitMqConfig.TOPIC_QUEUE_ONE)})
//    public void receiverMqExchage(String msg, Channel channel, Message message) throws IOException, TimeoutException {
//
//        long deliveryTag = message.getMessageProperties().getDeliveryTag();
//
//        try {
//            log.info("接收到消息啦 .................. {} ", msg);
//            //发生异常
////            log.error("发生异常");
////            int i = 1 / 0;
//            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
//            channel.basicAck(deliveryTag, false);
//        } catch (Exception e) {
////            log.error("接收消息失败,重新放回队列");
//            //requeu，为true，代表重新放入队列多次失败重新放回会导致队列堵塞或死循环问题，
//            // 解决方案，剔除此消息，然后记录到db中去补偿
//            //channel.basicNack(deliveryTag, false, true);
//            //拒绝消息
//            //channel.basicReject(deliveryTag, true);
//            log.info(" ................. insert to DB history .................");
//            log.info(message.toString());
//            channel.basicNack(deliveryTag,false,true);
//        }finally {
////            channel.close();
//        }
//    }
//}
