package com.example.aichatbot.service;

import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final OllamaService ollamaService;

    public ChatService(OllamaService ollamaService){
        this.ollamaService = ollamaService;
    }

    public String askAI(String message){

        String prompt = "You are an expert software developer. Answer: " + message;

        return ollamaService.generateResponse(prompt);

    }

}