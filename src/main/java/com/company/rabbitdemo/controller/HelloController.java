package com.company.rabbitdemo.controller;

import com.company.rabbitdemo.model.Message;
import com.company.rabbitdemo.service.HelloService;
import com.company.rabbitdemo.service.MessageListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rabbitmq")
public class HelloController {

    private final HelloService helloService;
    private final MessageListener messageListener;

    public HelloController(HelloService helloService, MessageListener messageListener) {
        this.helloService = helloService;
        this.messageListener = messageListener;
    }


    @GetMapping
    public ResponseEntity<String> helloWorld(@RequestParam(value = "message") String message) {
        helloService.sentRabbitMessage(message);
        return ResponseEntity.ok("Mesaj Alindi");
    }

    @GetMapping("/m")
    public ResponseEntity<List<Message>> receiveMessages() {
        return ResponseEntity.ok(messageListener.messages());
    }
}
