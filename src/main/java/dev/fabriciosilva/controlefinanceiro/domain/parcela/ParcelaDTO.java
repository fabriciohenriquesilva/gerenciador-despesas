package dev.fabriciosilva.controlefinanceiro.domain.parcela;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro.dto.MovimentoFinanceiroCreateRequest;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParcelaDTO {

    private Integer id;

    @NotNull(message = "A parcela precisa estar vinculada a uma Movimentação Financeira")
    @JsonBackReference
    private MovimentoFinanceiroCreateRequest movimentoFinanceiroCreateRequest;

    @NotNull(message = "Informe o número da parcela")
    private Integer numero;

    @NotNull(message = "Informe a quantidade de parcelas")
    private Integer quantidade;

    @NotNull(message = "Informe o valor total da parcela")
    private BigDecimal valorTotal;

    private BigDecimal desconto;

    private BigDecimal valorLiquido;

    @NotNull(message = "Informe a data de pagamento da parcela")
    private LocalDate dataPagamento;

    @NotNull(message = "Informe a data de vencimento da parcela")
    private LocalDate dataVencimento;

    private FormaPagamento formaPagamento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MovimentoFinanceiroCreateRequest getMovimentoFinanceiroDTO() {
        return movimentoFinanceiroCreateRequest;
    }

    public void setMovimentoFinanceiroDTO(MovimentoFinanceiroCreateRequest movimentoFinanceiroCreateRequest) {
        this.movimentoFinanceiroCreateRequest = movimentoFinanceiroCreateRequest;
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

    public FormaPagamento getFormaDePagamento() {
        return formaPagamento;
    }

    public void setFormaDePagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}
