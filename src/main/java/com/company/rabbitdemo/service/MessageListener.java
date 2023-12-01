package com.company.rabbitdemo.service;

import com.company.rabbitdemo.model.Message;
import com.company.rabbitdemo.repository.MessageRepository;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageListener {

    private final AmqpTemplate rabbitTemplate;
    private final MessageRepository messageRepository;

    public MessageListener(AmqpTemplate rabbitTemplate, MessageRepository messageRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.messageRepository = messageRepository;
    }

    public List<Message> messages(){
        return messageRepository.findAll();
    }

    @RabbitListener(queues = "${sample.rabbitmq.queue}")
    public void receiveMessages(String message) {
        messageRepository.save(new Message(null,message));
    }
}
