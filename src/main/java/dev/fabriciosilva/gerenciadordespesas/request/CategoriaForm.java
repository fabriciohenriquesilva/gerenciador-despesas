package dev.fabriciosilva.gerenciadordespesas.request;

import javax.validation.constraints.NotBlank;

public class CategoriaForm {

    @NotBlank(message = "A categoria precisa ter um nome")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
