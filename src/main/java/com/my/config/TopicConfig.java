package com.my.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hutf
 * @createTime 2020年11月10日 09:58:00
 * 主题模式
 * 一个交换机 binding  多个队列,一个队列可以接收多个交换机的消息
 *  N ： N
 */
@Configuration
public class TopicConfig {

    @Bean
    public Queue topicQueue() {
        return new Queue("topicQueue");
    }

    @Bean
    public Queue topicQueueSecond() {
        return new Queue("topicQueueSecond");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    public Binding rabbitBinding() {
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with("rabbit.*");
    }

    @Bean
    public Binding topicBinding() {
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with("*.topic");
    }


    @Bean
    public Binding rabbitBinding2() {
        return BindingBuilder.bind(topicQueueSecond()).to(topicExchange()).with("rabbit.*");
    }

    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicQueueSecond()).to(topicExchange()).with("*.topic");
    }

}
