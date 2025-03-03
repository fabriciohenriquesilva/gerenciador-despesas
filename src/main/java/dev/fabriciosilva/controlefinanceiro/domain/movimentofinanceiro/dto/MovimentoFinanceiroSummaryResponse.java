package dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.fabriciosilva.controlefinanceiro.domain.categoria.CategoriaDTO;
import dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro.TipoMovimentoFinanceiro;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovimentoFinanceiroSummaryResponse {

    private Integer id;

    private String descricao;

    private BigDecimal valor;

    private TipoMovimentoFinanceiro tipoMovimentoFinanceiro;

    private LocalDate data;

    private CategoriaDTO categoria;

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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

}
