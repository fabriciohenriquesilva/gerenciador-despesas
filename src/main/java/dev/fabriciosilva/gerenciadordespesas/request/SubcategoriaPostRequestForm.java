package dev.fabriciosilva.gerenciadordespesas.request;

import dev.fabriciosilva.gerenciadordespesas.domain.Categoria;
import dev.fabriciosilva.gerenciadordespesas.domain.Subcategoria;

import javax.validation.constraints.NotBlank;

public class SubcategoriaPostRequestForm {

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

    public Subcategoria toSubcategoria(){
        Subcategoria subcategoria = new Subcategoria();
        subcategoria.setNome(this.nome);

        return subcategoria;
    }
}
