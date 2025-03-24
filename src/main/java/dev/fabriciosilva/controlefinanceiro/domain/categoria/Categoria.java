package dev.fabriciosilva.controlefinanceiro.domain.categoria;

import dev.fabriciosilva.controlefinanceiro.domain.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    private Categoria pai;

    @OneToMany(mappedBy = "pai", fetch = FetchType.LAZY)
    private Set<Categoria> subcategorias;

    @Column(length = 7)
    private String cor;

    @Column(nullable = false)
    private LocalDate processamento;

    @Column(nullable = false)
    private LocalDate atualizacao;

    @Version
    private Integer version;

    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    private User usuario;

    @PrePersist
    public void prePersist() {
        this.processamento = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.atualizacao = LocalDate.now();
    }

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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
