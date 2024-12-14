package com.appmeteorologicoprodutor;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private String exchange = "alertaExchange";
    private String routingKey = "alertaRoutingKey";

    public void enviarMensagem(String mensagem) {
        amqpTemplate.convertAndSend(exchange, routingKey, mensagem);
    }
}
