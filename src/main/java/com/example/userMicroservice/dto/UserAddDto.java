package com.example.userMicroservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserAddDto {
    private String email;
    private String password;
    private String role;
    private String name;
}
