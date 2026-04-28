package com.springboot.myapp;

import com.springboot.myapp.dto.OllamaResponse;
import com.springboot.myapp.dto.PromptDto;
import com.springboot.myapp.service.OllamaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ollama")
@AllArgsConstructor
public class OllamaController {
    private final OllamaService ollamaService;

    @PostMapping("/chat")
    public ResponseEntity<OllamaResponse> promptAPI(@Valid @RequestBody PromptDto promptDto){
        return ResponseEntity
                        .ok(ollamaService.chat(promptDto));
    }
}