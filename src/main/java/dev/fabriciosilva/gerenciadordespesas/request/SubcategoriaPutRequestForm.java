package dev.fabriciosilva.gerenciadordespesas.request;

import dev.fabriciosilva.gerenciadordespesas.domain.Subcategoria;

import javax.validation.constraints.NotBlank;

public class SubcategoriaPutRequestForm {

    private String id;
    @NotBlank(message = "A subcategoria precisa ter um nome")
    private String nome;

    private String categoria;

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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Subcategoria toSubcategoria(){
        Subcategoria subcategoria = new Subcategoria();
        subcategoria.setId(Long.valueOf(id));
        subcategoria.setNome(this.nome);

        return subcategoria;
    }
}
