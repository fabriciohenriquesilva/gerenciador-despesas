package dev.fabriciosilva.controlefinanceiro.domain.user;

import dev.fabriciosilva.controlefinanceiro.domain.user.dto.UserCreationDto;
import dev.fabriciosilva.controlefinanceiro.domain.user.dto.UserLoginDto;
import dev.fabriciosilva.controlefinanceiro.domain.user.dto.UserResponseDto;
import dev.fabriciosilva.controlefinanceiro.domain.user.dto.UserTokenDto;
import dev.fabriciosilva.controlefinanceiro.domain.user.mapper.UserMapper;
import dev.fabriciosilva.controlefinanceiro.infra.exception.RecursoInexistenteException;
import dev.fabriciosilva.controlefinanceiro.infra.security.TokenService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class UserService {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserService(TokenService tokenService, AuthenticationManager authenticationManager, UserMapper userMapper, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponseDto create(UserCreationDto userCreationDto) {
        var user = userMapper.toEntity(userCreationDto);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        return userMapper.toResponseDto(user);
    }

    @Transactional
    public UserTokenDto login(UserLoginDto userLoginDto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword());
        var authentication = authenticationManager.authenticate(authenticationToken);
        String token = tokenService.generateToken((User) authentication.getPrincipal());
        return new UserTokenDto(token);
    }

    public UserResponseDto getUserById(Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RecursoInexistenteException(userId));
        return userMapper.toResponseDto(user);
    }

    public Page<UserResponseDto> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toResponseDto);
    }

}
