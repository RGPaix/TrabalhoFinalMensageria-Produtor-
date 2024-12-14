package com.appmeteorologicoprodutor;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.*;

@Configuration
@EnableRabbit
public class RabbitConfig {

    @Bean
    public DirectExchange alertaExchange() {
        return new DirectExchange("alertaExchange");
    }
    @Bean
    public Queue alertaQueue() {
        return new Queue("alertaQueue", true);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(alertaQueue()).to(alertaExchange()).with("alertaRoutingKey");
    }
}