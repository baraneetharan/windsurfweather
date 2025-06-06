package com.weatherbot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.weatherbot.config.Assistant;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final Assistant assistant;

    public ChatController(Assistant assistant) {
        this.assistant = assistant;
    }

    @PostMapping
    public String chat(@RequestBody String userMessage) {
        return assistant.chat(userMessage);
    }
}
