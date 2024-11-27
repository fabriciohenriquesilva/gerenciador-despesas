package dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.fabriciosilva.controlefinanceiro.domain.categoria.CategoriaDTO;
import dev.fabriciosilva.controlefinanceiro.domain.parcela.ParcelaDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovimentoFinanceiroDTO {

    private Integer id;

    @NotBlank(message = "Informe a descrição da movimentação finaceira")
    private String descricao;

    @NotNull(message = "Informe o valor da movimentação financeira")
    private BigDecimal valor;

    @NotNull(message = "Informe o tipo da movimentação financeira")
    private TipoMovimentoFinanceiro tipoMovimentoFinanceiro;

    @NotNull(message = "Informe a data da movimentação financeira")
    private LocalDate data;

    @NotNull(message = "Informe a quantidade de parcelas da movimentação financeira")
    private Integer quantidadeParcelas;

    @NotNull(message = "Informe a categoria da movimentação financeira")
    private CategoriaDTO categoria;

    @JsonManagedReference
    private List<ParcelaDTO> parcelas;

    public void addParcela(ParcelaDTO parcelaDTO) {
        if (this.parcelas == null) {
            this.parcelas = new ArrayList<>();
        }
        this.parcelas.add(parcelaDTO);
    }

    // gets and sets

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

    public Integer getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(Integer quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    public List<ParcelaDTO> getParcelas() {
        return parcelas;
    }

//    public void setListaDeParcelas(List<ParcelaDTO> listaDeParcelas) {
//        this.listaDeParcelas = listaDeParcelas;
//    }

}
