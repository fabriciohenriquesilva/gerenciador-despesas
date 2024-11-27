package dev.fabriciosilva.controlefinanceiro.domain.categoria;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoriaDTO {

    private Integer id;

    @NotBlank(message = "A categoria precisa ter um nome")
    private String nome;

    private CategoriaDTO pai;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CategoriaDTO getPai() {
        return pai;
    }

    public void setPai(CategoriaDTO pai) {
        this.pai = pai;
    }
}
