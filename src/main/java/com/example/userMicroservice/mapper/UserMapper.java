package com.example.userMicroservice.mapper;

import com.example.userMicroservice.dto.UserAddDto;
import com.example.userMicroservice.dto.UserDto;
import com.example.userMicroservice.model.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.stereotype.Component;

@With
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserMapper {
    private User user;
    private UserDto userDto;
    private UserAddDto userAddDto;

    public UserDto toDto() {
        return toUserDto(user);
    }

    public User toEntity() {
        return toUserEntity(userDto);
    }

    public User addDtoToEntity() {
        return toUserEntity(userAddDto);
    }

    public UserAddDto toUserAddDto(User user) {
        UserAddDto userAddDto = new UserAddDto();

        userAddDto.setEmail(user.getEmail());
        userAddDto.setPassword(user.getPassword());
        userAddDto.setRole(user.getRole());
        userAddDto.setName(user.getName());

        return userAddDto;
    }

    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        userDto.setName(user.getName());

        return userDto;
    }

    public User toUserEntity(UserAddDto userAddDto) {
        User user = new User();

        user.setEmail(userAddDto.getEmail());
        user.setPassword(userAddDto.getPassword());
        user.setRole(userAddDto.getRole());
        user.setName(userAddDto.getName());

        return user;
    }

    public User toUserEntity(UserDto userDto) {
        User user = new User();

        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setName(userDto.getName());

        return user;
    }
}
