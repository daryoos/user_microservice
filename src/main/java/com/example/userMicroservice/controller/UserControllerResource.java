package com.example.userMicroservice.controller;

import com.example.userMicroservice.dto.UserAddDto;
import com.example.userMicroservice.dto.UserDto;
import com.example.userMicroservice.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/users")
public interface UserControllerResource {

    @GetMapping(value = "/getById/{id}")
    ResponseEntity<User> getById(@PathVariable Long id);

    @PostMapping(value = "/add")
    ResponseEntity<String> add(@RequestBody UserAddDto userAddDto);

    @PutMapping(value = "/update")
    ResponseEntity<String> update(@RequestBody UserDto userDto);

    @DeleteMapping(value = "/deleteById/{id}")
    ResponseEntity<String> deleteById(@PathVariable Long id);

    @GetMapping(value = "/getAll")
    ResponseEntity<List<User>> getAll();
}
