package dev.fabriciosilva.gerenciadordespesas.dto;

import dev.fabriciosilva.gerenciadordespesas.domain.Categoria;

import javax.validation.constraints.NotBlank;

public class CategoriaDto {

    private String id;
    @NotBlank(message = "A categoria precisa ter um nome")
    private String nome;

    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId().toString();
        this.nome = categoria.getNome();
    }

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

}
