package com.springboot.myapp.service;

import com.springboot.myapp.model.Customer;
import com.springboot.myapp.model.Document;
import com.springboot.myapp.repository.DocumentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
@Slf4j
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final CustomerService customerService;
    // Point this to the path of your UI's public folder
    private final static String UPLOAD_PATH = "D:/java-fsd-hex-march-2026/React/trs-ui/public/uploads";

    public Document upload(String customerUsername, MultipartFile file) throws IOException {
        Customer customer = customerService.getByUsername(customerUsername);

        // Create a File handler to save the directory path
        File directory = new File(UPLOAD_PATH);

        // Fetch the file name :- to save it in DB name.extension -split(.)[1]
        String fileName =  file.getOriginalFilename();

        // Prepare the Path using java.nio
        Path path =  Paths.get(UPLOAD_PATH + "/" + fileName);

        Files.write(path, file.getBytes());

        //save the path in DB
        Document document = new Document();
        document.setCustomer(customer);
        document.setProfileImage(fileName);
        return documentRepository.save(document);
    }
}