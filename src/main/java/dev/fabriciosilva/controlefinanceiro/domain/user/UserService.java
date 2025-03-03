package dev.fabriciosilva.controlefinanceiro.domain.user;

import dev.fabriciosilva.controlefinanceiro.core.AbstractService;
import dev.fabriciosilva.controlefinanceiro.core.EmailService;
import dev.fabriciosilva.controlefinanceiro.domain.passwordreset.PasswordResetToken;
import dev.fabriciosilva.controlefinanceiro.domain.passwordreset.PasswordResetTokenService;
import dev.fabriciosilva.controlefinanceiro.domain.passwordreset.dto.PasswordResetTokenRequest;
import dev.fabriciosilva.controlefinanceiro.domain.passwordreset.dto.ResetPasswordRequest;
import dev.fabriciosilva.controlefinanceiro.domain.user.dto.UserCreateRequest;
import dev.fabriciosilva.controlefinanceiro.domain.user.dto.UserLoginRequest;
import dev.fabriciosilva.controlefinanceiro.domain.user.dto.UserResponseDetail;
import dev.fabriciosilva.controlefinanceiro.domain.user.dto.UserTokenDto;
import dev.fabriciosilva.controlefinanceiro.domain.user.mapper.UserMapper;
import dev.fabriciosilva.controlefinanceiro.infra.exception.RecursoInexistenteException;
import dev.fabriciosilva.controlefinanceiro.infra.exception.ServiceException;
import dev.fabriciosilva.controlefinanceiro.infra.exception.TokenInvalidoException;
import dev.fabriciosilva.controlefinanceiro.infra.security.TokenService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.util.UUID;

@Service
public class UserService extends AbstractService<User, Integer> {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordResetTokenService passwordResetTokenService;
    private final EmailService emailService;

    public UserService(TokenService tokenService, AuthenticationManager authenticationManager,
                       UserMapper userMapper, UserRepository userRepository, PasswordResetTokenService passwordResetTokenService,
                       EmailService emailService) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordResetTokenService = passwordResetTokenService;
        this.emailService = emailService;
    }

    @Override
    public UserRepository getRepository() {
        return this.userRepository;
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

    public void generatePasswordResetToken(@Valid PasswordResetTokenRequest form) {
        User user = userRepository.findByEmail(form.getEmail()).orElse(null);

        if (user == null) {
            return;
        }

        // verificar se já não existe um token para o user que não esteja expirado
        PasswordResetToken tokenActiveByUser = passwordResetTokenService.findTokenActiveByUser(user);

        if (tokenActiveByUser == null) {
            String token = UUID.randomUUID().toString();
            PasswordResetToken resetToken = new PasswordResetToken(user, token, 15);
            tokenActiveByUser = passwordResetTokenService.save(resetToken);
        }

        try {
            emailService.sendPasswordResetEmail(form.getEmail(), tokenActiveByUser.getToken());
        } catch (MessagingException e) {
            throw new ServiceException("Erro ao enviar o email de recuperação de senha. Favor entrar em contato com o suporte técnico.");
        } catch (MailAuthenticationException e) {
            throw new ServiceException("Erro na autenticação do serviço de envio de email. Favor entrar em contato com o suporte técnico.");
        }

    }

    public void resetPassword(ResetPasswordRequest form) {
        // buscar o token no DB
        String token = form.getToken();
        PasswordResetToken resetToken = passwordResetTokenService.findByToken(token);

        // Verificar se o token está ativo
        if (resetToken == null || resetToken.isExpired()) {
            throw new TokenInvalidoException("O Token informado está inválido ou expirado");
        }

        String newPassword = new BCryptPasswordEncoder().encode(form.getNewPassword());

        User user = resetToken.getUser();
        user.setPassword(newPassword);
        userRepository.save(user);

        // Remover o token após o uso
        passwordResetTokenService.delete(resetToken);
    }
}
