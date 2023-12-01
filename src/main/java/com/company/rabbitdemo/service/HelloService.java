package com.company.rabbitdemo.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    private final AmqpTemplate rabbitTemplate;


    public HelloService(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sentRabbitMessage(String message) {
        rabbitTemplate.convertAndSend("default", "sample.routingKey", message);
    }
}
