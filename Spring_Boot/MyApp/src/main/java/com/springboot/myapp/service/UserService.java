package com.springboot.myapp.service;

import com.springboot.myapp.model.User;
import com.springboot.myapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void addUser(User user) {
        userRepository.save(user);
    }
}