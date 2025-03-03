package dev.fabriciosilva.controlefinanceiro.domain.user.mapper;

import dev.fabriciosilva.controlefinanceiro.domain.user.User;
import dev.fabriciosilva.controlefinanceiro.domain.user.dto.UserCreateRequest;
import dev.fabriciosilva.controlefinanceiro.domain.user.dto.UserResponseDetail;
import dev.fabriciosilva.controlefinanceiro.domain.user.dto.UserUpdateRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User toEntity(UserCreateRequest userCreateRequest) {
        var user = new User();
        BeanUtils.copyProperties(userCreateRequest, user);
        var encryptedPassword = passwordEncoder.encode(userCreateRequest.getPassword());
        user.setPassword(encryptedPassword);
        return user;
    }

    public User toEntity(UserUpdateRequest userUpdateRequest) {
        User user = new User();
        BeanUtils.copyProperties(userUpdateRequest, user);
        return user;
    }

    public UserResponseDetail toResponse(User user) {
        var userResponseDto = new UserResponseDetail();
        BeanUtils.copyProperties(user, userResponseDto);
        return userResponseDto;
    }
}
