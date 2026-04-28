package com.springboot.myapp.controller;

import com.springboot.myapp.model.Document;
import com.springboot.myapp.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api/document")
@AllArgsConstructor
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping("/upload")
    public Document upload(Principal principal,
                           @RequestParam("file") MultipartFile file) throws IOException {
        String customerUsername = principal.getName();
        return documentService.upload(customerUsername,file);
    }
}