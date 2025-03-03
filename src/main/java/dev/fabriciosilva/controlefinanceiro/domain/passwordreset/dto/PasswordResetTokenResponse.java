package dev.fabriciosilva.controlefinanceiro.domain.passwordreset.dto;

public class PasswordResetTokenResponse {

    private String message;

    public PasswordResetTokenResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
