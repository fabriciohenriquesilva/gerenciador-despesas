package dev.fabriciosilva.controlefinanceiro.domain.passwordreset.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class PasswordResetTokenRequest {
    
    @Email
    @NotBlank(message = "Informe o e-mail para recuperar a senha")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
