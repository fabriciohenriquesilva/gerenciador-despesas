package dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro;

import dev.fabriciosilva.controlefinanceiro.domain.categoria.Categoria;
import dev.fabriciosilva.controlefinanceiro.domain.parcela.Parcela;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "movimentofinanceiro")
public class MovimentoFinanceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descricao;

    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoMovimentoFinanceiro tipoMovimentoFinanceiro;

    @ManyToOne
    @JoinColumn(name = "categoria", referencedColumnName = "id")
    private Categoria categoria;

    @OneToMany(mappedBy = "movimentoFinanceiro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Parcela> parcelas;

    // usuário

    private LocalDate data;

    private LocalDate processamento;

    private LocalDate atualizacao;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TipoMovimentoFinanceiro getTipoMovimentoFinanceiro() {
        return tipoMovimentoFinanceiro;
    }

    public void setTipoMovimentoFinanceiro(TipoMovimentoFinanceiro tipoMovimentoFinanceiro) {
        this.tipoMovimentoFinanceiro = tipoMovimentoFinanceiro;
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovimentoFinanceiro)) return false;
        MovimentoFinanceiro that = (MovimentoFinanceiro) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
