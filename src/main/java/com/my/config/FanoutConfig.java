package com.my.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hutf
 * @createTime 2020年11月10日 10:00:00
 * 声明两个 queue ，分别绑定到同一个 交换机上，
 * 广播模式，无需路由键
 */
@Configuration
public class FanoutConfig {

    @Bean
    public Queue firstFanoutQueue() {
        return new Queue("firstFanoutQueue");
    }

    @Bean
    public Binding firstFanoutBinding() {
        return BindingBuilder.bind(firstFanoutQueue()).to(fanoutExchange());
    }

    @Bean
    public Queue secondFanoutQueue() {
        return new Queue("secondFanoutQueue");
    }

    @Bean
    public Binding secondFanoutBinding() {
        return BindingBuilder.bind(secondFanoutQueue()).to(fanoutExchange());
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

}
