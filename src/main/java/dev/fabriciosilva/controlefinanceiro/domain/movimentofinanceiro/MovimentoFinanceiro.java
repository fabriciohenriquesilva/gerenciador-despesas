package dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "movimentofinanceiro")
public class MovimentoFinanceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descricao;

    private BigDecimal valor;

    private Tipo tipo;

    // usuário

    private LocalDate processamento;

    private  LocalDate atualizacao;

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

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
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
        if (!(o instanceof MovimentoFinanceiro)) return false;
        MovimentoFinanceiro that = (MovimentoFinanceiro) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
