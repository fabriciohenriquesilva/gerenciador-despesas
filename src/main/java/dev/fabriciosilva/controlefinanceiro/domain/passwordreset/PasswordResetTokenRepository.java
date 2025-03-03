package dev.fabriciosilva.controlefinanceiro.domain.passwordreset;

import dev.fabriciosilva.controlefinanceiro.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {

    List<PasswordResetToken> findByUser(User user);

    PasswordResetToken findByToken(String token);
}
