package dev.fabriciosilva.gerenciadordespesas.request;

import javax.validation.constraints.NotBlank;

public class SubcategoriaForm {

    @NotBlank(message = "A subcategoria precisa ter um nome")
    private String nome;

    private String categoria;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
