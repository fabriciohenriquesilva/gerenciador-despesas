package dev.fabriciosilva.controlefinanceiro.domain.parcela.dto;

import dev.fabriciosilva.controlefinanceiro.domain.parcela.FormaPagamento;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ParcelaPagamentoRequest {

    @NotNull(message = "O ID da parcela não pode ser nulo")
    private Integer id;

    private BigDecimal desconto;

    @NotNull(message = "Informe o valor líquido da parcela")
    private BigDecimal valorLiquido;

    @NotNull(message = "Informe a data de pagamento")
    private LocalDate dataPagamento;

    @NotNull(message = "Informe a forma de pagamento")
    private FormaPagamento formaPagamento;

    private Integer contaBanco;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Integer getContaBanco() {
        return contaBanco;
    }

    public void setContaBanco(Integer contaBanco) {
        this.contaBanco = contaBanco;
    }
}
