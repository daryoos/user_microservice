package com.example.userMicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeviceDto {
    private Long id;
    private Long userId;
    private String description;
    private String address;
    private Float mhec;
}
