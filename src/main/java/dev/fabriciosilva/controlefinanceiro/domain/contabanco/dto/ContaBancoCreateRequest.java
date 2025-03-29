package dev.fabriciosilva.controlefinanceiro.domain.contabanco.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class ContaBancoCreateRequest {

    @NotBlank(message = "Informe um nome para a conta bancária. Ex: Banco do Brasil")
    private String nome;

    @Pattern(regexp = "^#([A-Fa-f0-9]{6})$", message = "Formato de cor inválido. Use #RRGGBB")
    private String cor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
