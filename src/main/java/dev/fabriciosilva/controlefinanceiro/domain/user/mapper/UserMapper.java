package dev.fabriciosilva.controlefinanceiro.domain.user.mapper;

import dev.fabriciosilva.controlefinanceiro.domain.user.User;
import dev.fabriciosilva.controlefinanceiro.domain.user.dto.UserCreationDto;
import dev.fabriciosilva.controlefinanceiro.domain.user.dto.UserResponseDto;
import dev.fabriciosilva.controlefinanceiro.domain.user.dto.UserUpdateDto;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User toEntity(UserCreationDto userCreationDto) {
        var user = new User();
        BeanUtils.copyProperties(userCreationDto, user);
        var encryptedPassword = passwordEncoder.encode(userCreationDto.getPassword());
        user.setPassword(encryptedPassword);
        return user;
    }

    public User toEntity(UserUpdateDto userUpdateDto) {
        User user = new User();
        BeanUtils.copyProperties(userUpdateDto, user);
        return user;
    }

    public UserResponseDto toResponseDto(User user) {
        var userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(user, userResponseDto);
        return userResponseDto;
    }
}
