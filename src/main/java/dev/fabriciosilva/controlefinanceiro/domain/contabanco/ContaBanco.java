package dev.fabriciosilva.controlefinanceiro.domain.contabanco;

import dev.fabriciosilva.controlefinanceiro.domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "contabanco")
public class ContaBanco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Column(length = 7)
    private String cor;

    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    private User usuario;

    @Column(nullable = false)
    private LocalDateTime cadastro;

    private LocalDateTime atualizacao;

    @Version
    private Integer version;

    @PrePersist
    public void prePersist() {
        this.cadastro = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.atualizacao = LocalDateTime.now();
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

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getCadastro() {
        return cadastro;
    }

    public void setCadastro(LocalDateTime cadastro) {
        this.cadastro = cadastro;
    }

    public LocalDateTime getAtualizacao() {
        return atualizacao;
    }

    public void setAtualizacao(LocalDateTime atualizacao) {
        this.atualizacao = atualizacao;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
