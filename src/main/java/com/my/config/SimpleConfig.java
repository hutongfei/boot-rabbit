package com.my.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hutf
 * @createTime 2020年11月10日 09:56:00
 */
@Configuration
public class SimpleConfig {

    @Bean
    public Queue simpleQueue() {
        return new Queue("simpleQueue",true);
    }
}
