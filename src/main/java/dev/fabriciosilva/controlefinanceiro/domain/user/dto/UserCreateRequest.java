package dev.fabriciosilva.controlefinanceiro.domain.user.dto;

import javax.validation.constraints.NotBlank;

public class UserCreateRequest {

    @NotBlank(message = "Nome de usuário não pode ser nulo/vazio")
    private String username;

    @NotBlank(message = "Senha de usuário não pode ser nulo/vazio")
    private String password;

    @NotBlank(message = "O nome não pode ser nulo/vazio")
    private String nome;

    @NotBlank(message = "O email não pode ser nulo/vazio")
    private String email;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
