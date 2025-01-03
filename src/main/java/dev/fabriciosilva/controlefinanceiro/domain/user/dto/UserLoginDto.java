package dev.fabriciosilva.controlefinanceiro.domain.user.dto;

import javax.validation.constraints.NotBlank;

public class UserLoginDto {

    @NotBlank(message = "Nome de usuário não pode ser nulo/vazio")
    private String username;

    @NotBlank(message = "Nome de usuário não pode ser nulo/vazio")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
