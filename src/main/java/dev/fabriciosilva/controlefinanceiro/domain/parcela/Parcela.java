package dev.fabriciosilva.controlefinanceiro.domain.parcela;

import dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro.MovimentoFinanceiro;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "parcela")
public class Parcela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private MovimentoFinanceiro movimentoFinanceiro;

    private Integer numero;

    private Integer quantidade;

    private BigDecimal valorTotal;

    private BigDecimal desconto;

    private BigDecimal valorLiquido;

    private LocalDate dataPagamento;

    private LocalDate dataVencimento;

    @Enumerated(EnumType.STRING)
    private FormaDePagamento formaDePagamento;

    // usuario

    private LocalDate processamento;

    private  LocalDate atualizacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MovimentoFinanceiro getMovimentoFinanceiro() {
        return movimentoFinanceiro;
    }

    public void setMovimentoFinanceiro(MovimentoFinanceiro movimentoFinanceiro) {
        this.movimentoFinanceiro = movimentoFinanceiro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public BigDecimal getValorLiquido() {
        return valorLiquido;
    }

    public void setValorLiquido(BigDecimal valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
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
        if (!(o instanceof Parcela)) return false;
        Parcela parcela = (Parcela) o;
        return Objects.equals(id, parcela.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
