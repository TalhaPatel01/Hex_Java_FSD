package com.springboot.myapp.service;

import com.springboot.myapp.dto.OllamaResponse;
import com.springboot.myapp.dto.PromptDto;
import lombok.AllArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class OllamaService {
    private final ChatClient chatClient;

    // You are initializing this chat client without any context
    public OllamaService(ChatClient.Builder builder ){
        chatClient= builder
                .build();
    }

    public OllamaResponse chat(PromptDto promptDto) {
        //   return  new OllamaResponse("working");
        return new OllamaResponse(chatClient
                .prompt()
                .user(promptDto.prompt())
                .call()
                .content());
    }
}