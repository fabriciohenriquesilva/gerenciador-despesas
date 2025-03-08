package dev.fabriciosilva.controlefinanceiro.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import dev.fabriciosilva.controlefinanceiro.domain.user.User;
import dev.fabriciosilva.controlefinanceiro.domain.user.UserRepository;
import dev.fabriciosilva.controlefinanceiro.infra.exception.TokenGenerationException;
import dev.fabriciosilva.controlefinanceiro.infra.exception.TokenInvalidoException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private final UserRepository userRepository;

    @Value("${api.security.token.secret}")
    private String tokenSecret;

    public TokenService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateToken(User user) {

        var userFound = userRepository.findByUsername(user.getUsername());

        try {
            var algorithm = Algorithm.HMAC256(tokenSecret);
            return JWT.create()
                    .withIssuer("Kofrinho API")
                    .withSubject(user.getUsername())
                    .withClaim("user", user.getUsername())
                    .withClaim("id", user.getId())
                    .withExpiresAt(generateTokenValidityPeriod())
                    .sign(algorithm);
        } catch (JWTCreationException ex) {
            throw new TokenGenerationException("Erro ao criar Token de Acesso para o usuário");
        }

    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(tokenSecret);
            return JWT.require(algoritmo)
                    .withIssuer("Kofrinho API")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new TokenInvalidoException("Token inválido ou expirado");
        }
    }

    private Instant generateTokenValidityPeriod() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }

}
