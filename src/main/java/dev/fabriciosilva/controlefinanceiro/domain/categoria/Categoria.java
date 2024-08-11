package dev.fabriciosilva.controlefinanceiro.domain.categoria;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToOne
    private Categoria pai;

    @OneToMany(mappedBy = "pai")
    private Set<Categoria> subcategorias;

    // usuario

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

    private LocalDate processamento;

    private LocalDate atualizacao;

    public Categoria getPai() {
        return pai;
    }

    public void setPai(Categoria pai) {
        this.pai = pai;
    }

    public Set<Categoria> getSubcategorias() {
        return subcategorias;
    }

    public void setSubcategorias(Set<Categoria> subcategorias) {
        this.subcategorias = subcategorias;
    }

    public LocalDate getProcessamento() {
        return processamento;
    }

    public void setProcessamento(LocalDate processamento) {
        this.processamento = processamento;
    }

    public LocalDate getAtualizacao() {
        return atualizacao;
    }

    public void setAtualizacao(LocalDate atualizacao) {
        this.atualizacao = atualizacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria)) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(id, categoria.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
