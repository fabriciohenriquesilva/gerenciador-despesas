package dev.fabriciosilva.gerenciadordespesas.request;

import dev.fabriciosilva.gerenciadordespesas.domain.Categoria;

import javax.validation.constraints.NotBlank;

public class CategoriaPostRequestForm {

    @NotBlank(message = "A categoria precisa ter um nome")
    String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria toCategoria(){
        Categoria categoria = new Categoria();
        categoria.setNome(this.nome);

        return categoria;
    }
}
