package dev.fabriciosilva.controlefinanceiro.domain.categoria.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoriaDTO {

    private Integer id;

    @NotBlank(message = "A categoria precisa ter um nome")
    private String nome;

    private CategoriaDTO pai;

    @Pattern(regexp = "^#([A-Fa-f0-9]{6})$", message = "Formato de cor inválido. Use #RRGGBB")
    private String cor;

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

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
