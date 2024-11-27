package dev.fabriciosilva.controlefinanceiro.domain.parcela;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro.MovimentoFinanceiroDTO;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParcelaDTO {

    private Integer id;

    @NotNull(message = "A parcela precisa estar vinculada a uma Movimentação Financeira")
    @JsonBackReference
    private MovimentoFinanceiroDTO movimentoFinanceiroDTO;

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

    private FormaDePagamento formaDePagamento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MovimentoFinanceiroDTO getMovimentoFinanceiroDTO() {
        return movimentoFinanceiroDTO;
    }

    public void setMovimentoFinanceiroDTO(MovimentoFinanceiroDTO movimentoFinanceiroDTO) {
        this.movimentoFinanceiroDTO = movimentoFinanceiroDTO;
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
}
