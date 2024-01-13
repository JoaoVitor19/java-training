package com.example.javatraining.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/send-message")
    @SendTo("/topic/received-message")
    public String handleMessage(String message) {
        return "Received message: " + message;
    }
}
