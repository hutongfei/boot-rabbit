package com.my.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hutf
 * @createTime 2020年11月10日 09:59:00
 *  路由模式下，队列，交换机，绑定，路由键
 *  一个（交换机+ 路由键） 对应一个队列
 *  1   :   N
 */
@Configuration
public class DirectConfig {

    @Bean
    public Queue directQueue() {
        return new Queue("directQueue");
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    @Bean
    public Binding directBinding() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("directKey");
    }
}
