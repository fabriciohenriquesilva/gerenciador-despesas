package dev.fabriciosilva.controlefinanceiro.domain.passwordreset.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ResetPasswordRequest {

    @NotBlank(message = "Token não foi informado")
    private String token;

    @NotBlank(message = "A nova senha não foi informada")
    @Size(min = 6, max = 15)
    private String newPassword;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
