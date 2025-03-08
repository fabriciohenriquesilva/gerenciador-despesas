package dev.fabriciosilva.controlefinanceiro.infra.context;

import dev.fabriciosilva.controlefinanceiro.domain.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public User getUser() {
        Object principal = getAuthentication().getPrincipal();

        if (principal instanceof User) {
            return (User) principal;
        }

        return null;
    }
}
