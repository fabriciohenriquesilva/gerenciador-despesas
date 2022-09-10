package dev.fabriciosilva.gerenciadordespesas.request;

import dev.fabriciosilva.gerenciadordespesas.domain.Categoria;

import javax.validation.constraints.NotBlank;

public class CategoriaPutRequestForm {

    private String id;
    @NotBlank(message = "A categoria precisa ter um nome")
    private String nome;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria toCategoria(){
        Categoria categoria = new Categoria();
        categoria.setId(Long.valueOf(this.id));
        categoria.setNome(this.nome);

        return categoria;
    }
}
