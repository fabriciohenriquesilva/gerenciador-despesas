package dev.fabriciosilva.controlefinanceiro.infra.context;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {

    Authentication getAuthentication();

}
