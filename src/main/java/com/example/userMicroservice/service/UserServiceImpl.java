package com.example.userMicroservice.service;

import com.example.userMicroservice.dto.DeviceDto;
import com.example.userMicroservice.dto.UserDto;
import com.example.userMicroservice.mapper.UserMapper;
import com.example.userMicroservice.model.User;
import com.example.userMicroservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User getById(Long id) {
        User user = userRepository.findById(id);
        System.out.println("LOG:\n" + user + "\n");
        return user;
    }

    @Override
    public void add(User user) throws Exception {
        try {
            if (userRepository.findByEmail(user.getEmail()) == null) {
                userRepository.save(user);
            }
            else {
                throw new Exception("User with the same email already exists");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void update(UserDto userDto) throws Exception {
        User userUpdated = userRepository.findById(userDto.getId());
        if (userUpdated != null) {
            userUpdated.setEmail(userDto.getEmail());
            userUpdated.setPassword(userDto.getPassword());
            userUpdated.setRole(userDto.getRole());
            userUpdated.setName(userDto.getName());
            userRepository.save(userUpdated);
        }
        else {
            throw new Exception("User not found");
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        User user = userRepository.findById(id);
        if (user != null) {
            ResponseEntity<List<DeviceDto>> deviceDtos = restTemplate.exchange("http://localhost:8091/devices/getByUserId/{userId}", HttpMethod.GET,
                    null, new ParameterizedTypeReference<List<DeviceDto>>() {}, user.getId());
            if (deviceDtos.getBody() != null) {
                deviceDtos.getBody().forEach(deviceDto -> {
                    restTemplate.delete("http://localhost:8091/devices/deleteById/{id}", deviceDto.getId());
                });
            }

            userRepository.delete(user);
        }
        else {
            throw new Exception("User not found");
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
}
