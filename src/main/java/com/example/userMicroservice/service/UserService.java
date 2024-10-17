package com.example.userMicroservice.service;

import com.example.userMicroservice.dto.UserDto;
import com.example.userMicroservice.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    User getById(Long id);

    void add(User user) throws Exception;

    void update(UserDto userDto) throws Exception;

    void delete(Long id) throws Exception;

    List<User> getAll();
}
