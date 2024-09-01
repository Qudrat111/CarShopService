package com.example.carshopwithspringboot.service.impl;


import com.example.carshopwithspringboot.dto.UserDto;
import com.example.carshopwithspringboot.model.User;
import com.example.carshopwithspringboot.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public UserService(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }


    public User update(UserDto userDto) throws JsonProcessingException {
        String s = objectMapper.writeValueAsString(userDto);
        User user = objectMapper.readValue(s, User.class);
        User save = userRepository.save(user);
        return user;
    }

    public Integer delete(Integer userId) {
        userRepository.deleteById(userId);
        return userId;
    }


    public List<User> findAll() {
        List<User> all = userRepository.findAll();
        return all;
    }

}
