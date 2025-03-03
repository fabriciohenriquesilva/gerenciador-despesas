package dev.fabriciosilva.controlefinanceiro.domain.user;

import dev.fabriciosilva.controlefinanceiro.domain.user.dto.UserCreateRequest;
import dev.fabriciosilva.controlefinanceiro.domain.user.dto.UserLoginRequest;
import dev.fabriciosilva.controlefinanceiro.domain.user.dto.UserResponseDetail;
import dev.fabriciosilva.controlefinanceiro.domain.user.dto.UserTokenDto;
import dev.fabriciosilva.controlefinanceiro.domain.user.mapper.UserMapper;
import dev.fabriciosilva.controlefinanceiro.infra.exception.RecursoInexistenteException;
import dev.fabriciosilva.controlefinanceiro.infra.security.TokenService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

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
    public UserResponseDetail create(UserCreateRequest userCreateRequest, MultipartFile foto) throws IOException {
        User user = userMapper.toEntity(userCreateRequest);

        if (foto != null) {
            user.setProfilePic(foto.getBytes());
        }

        userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Transactional
    public UserTokenDto login(UserLoginRequest userLoginRequest) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(), userLoginRequest.getPassword());
        var authentication = authenticationManager.authenticate(authenticationToken);
        String token = tokenService.generateToken((User) authentication.getPrincipal());
        return new UserTokenDto(token);
    }

    public UserResponseDetail getUserById(Integer id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new RecursoInexistenteException(id));
        return userMapper.toResponse(user);
    }

    public Page<UserResponseDetail> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toResponse);
    }

    public byte[] getProfilePic(Integer id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new RecursoInexistenteException(id));

        if (user.getProfilePic() == null) {
            throw new RecursoInexistenteException("O usuário não tem foto de perfil");
        }

        return user.getProfilePic();
    }

}
