package dev.fabriciosilva.controlefinanceiro.domain.passwordreset;

import dev.fabriciosilva.controlefinanceiro.core.AbstractService;
import dev.fabriciosilva.controlefinanceiro.domain.user.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PasswordResetTokenService extends AbstractService<PasswordResetToken, Integer> {

    private final PasswordResetTokenRepository passwordResetTokenRepository;

    public PasswordResetTokenService(PasswordResetTokenRepository passwordResetTokenRepository) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    @Override
    public PasswordResetTokenRepository getRepository() {
        return this.passwordResetTokenRepository;
    }

    public PasswordResetToken findTokenActiveByUser(User user) {
        List<PasswordResetToken> users = passwordResetTokenRepository.findByUser(user);
        return users.stream().filter(token -> !token.isExpired()).findFirst().orElse(null);
    }

    public PasswordResetToken findByToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }
}
