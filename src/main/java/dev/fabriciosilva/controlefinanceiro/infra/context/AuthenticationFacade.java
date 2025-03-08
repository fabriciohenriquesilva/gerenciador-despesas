package dev.fabriciosilva.controlefinanceiro.infra.context;

import dev.fabriciosilva.controlefinanceiro.domain.user.User;
import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {

    Authentication getAuthentication();

    User getUser();

}
