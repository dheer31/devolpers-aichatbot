package com.example.aichatbot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.aichatbot.service.ChatService;

@Controller
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    @GetMapping("/")
    public String home(){
        return "chat";
    }

    @PostMapping("/chat")
    public String chat(@RequestParam String message, Model model){

        String reply = chatService.askAI(message);

        model.addAttribute("message", message);
        model.addAttribute("reply", reply);

        return "chat";
    }

}