package com.example.userMicroservice.controller;

import com.example.userMicroservice.dto.UserAddDto;
import com.example.userMicroservice.dto.UserDto;
import com.example.userMicroservice.mapper.UserMapper;
import com.example.userMicroservice.model.User;
import com.example.userMicroservice.repository.UserRepository;
import com.example.userMicroservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class UserController implements UserControllerResource {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResponseEntity<User> getById(Long id) {
        User user = userService.getById(id);

        return ResponseEntity.status(200).body(user);
    }

    @Override
    public ResponseEntity<String> add(UserAddDto userAddDto) {
        User user = userMapper.withUserAddDto(userAddDto).addDtoToEntity();
        user.setPassword(hashPassword(user.getPassword()));

        try {
            userService.add(user);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
        return ResponseEntity.status(200).body("User added successfully");
    }

    @Override
    public ResponseEntity<String> update(UserDto userDto) {
        try {
            userDto.setPassword(hashPassword(userDto.getPassword()));
            userService.update(userDto);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("User does not exist");
        }
        return ResponseEntity.status(200).body("User updated successfully");
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        try {
            userService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
        return ResponseEntity.status(200).body("User deleted successfully");
    }

    @Override
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll();
        return ResponseEntity.status(200).body(users);
    }
}
