package com.my.config;

import com.my.consumer.ConfirmHandler;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hutf
 * @createTime 2020年11月10日 14:12:00
 */
@Configuration
public class ConfirmConfig {

    @Value("${spring.rabbitmq.addresses}")
    private String addresses;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.publisher-confirms}")
    private String publisherConfirms;

    @Value("${spring.rabbitmq.publisher-returns}")
    private String publisherReturns;

    @Value("${spring.rabbitmq.template.mandatory}")
    private boolean mandatory;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Bean
    public Queue confirmQueue() {
        return new Queue("confirmQueue");
    }

    @Bean
    public DirectExchange confirmExchange(){
        return ExchangeBuilder.directExchange("confirmExchange").build();
    }

    @Bean
    public Binding confirmBinding() {
        return BindingBuilder.bind(confirmQueue()).to(confirmExchange()).with("confirmKey");
    }

//    @Bean("confirmRabbitTemplate")
//    public RabbitTemplate confirmRabbitTemplate() {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate();
//        ConfirmHandler confirm = new ConfirmHandler();
//        rabbitTemplate.setReturnCallback(confirm);
//        rabbitTemplate.setConfirmCallback(confirm);
//        return rabbitTemplate;
//    }


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setAddresses(this.addresses);
        cachingConnectionFactory.setUsername(this.username);
        cachingConnectionFactory.setPassword(this.password);
        cachingConnectionFactory.setVirtualHost(this.virtualHost);
        cachingConnectionFactory.setPort(5672);
        // 如果消息要设置成回调，则以下的配置必须要设置成true
        cachingConnectionFactory.setPublisherConfirms(true);
        cachingConnectionFactory.setPublisherReturns(true);
        return cachingConnectionFactory;
    }

    /**
     * 同时为了调用SpringBoot集成rabbitMQ提供的发送的方法，我们需要注入rabbitTemplate
     */
    @Bean("confirmRabbitTemplate")
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(this.connectionFactory());
        template.setMandatory(mandatory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

}
