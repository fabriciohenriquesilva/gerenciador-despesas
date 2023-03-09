package dev.fabriciosilva.gerenciadordespesas.dto;

import dev.fabriciosilva.gerenciadordespesas.domain.Subcategoria;

import javax.validation.constraints.NotBlank;

public class SubcategoriaDto {

    private Long id;
    @NotBlank(message = "A subcategoria precisa ter um nome")
    private String nome;

    private String categoria;

    public SubcategoriaDto() { }

    public SubcategoriaDto(Subcategoria subcategoria) {
        this.id = subcategoria.getId();
        this.nome = subcategoria.getNome();
        this.categoria = subcategoria.getCategoria().getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
