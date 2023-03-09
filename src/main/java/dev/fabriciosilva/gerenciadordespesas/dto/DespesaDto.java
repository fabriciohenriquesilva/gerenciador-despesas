package dev.fabriciosilva.gerenciadordespesas.dto;

import dev.fabriciosilva.gerenciadordespesas.domain.Despesa;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DespesaDto {

    private Integer id;
    private String descricao;
    private BigDecimal valorGasto;
    private LocalDate dataDespesa;
    private CategoriaDto categoria;
    private SubcategoriaDto subcategoria;
    private PessoaDto credor;

    public DespesaDto(Despesa despesa) {
        this.id = despesa.getId();
        this.descricao = despesa.getDescricao();
        this.valorGasto = despesa.getValorGasto();
        this.dataDespesa = despesa.getDataDespesa();

        if(despesa.getCategoria() != null) {
            this.categoria = new CategoriaDto(despesa.getCategoria());
        }

        if(despesa.getCredor() != null) {
            this.credor = new PessoaDto(despesa.getCredor());
        }

        if(despesa.getSubcategoria() != null) {
            this.subcategoria = new SubcategoriaDto(despesa.getSubcategoria());
        }
    }

    public static Page<DespesaDto> converterLista(Page<Despesa> despesas) {
        return despesas.map(DespesaDto::new);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorGasto() {
        return valorGasto;
    }

    public void setValorGasto(BigDecimal valorGasto) {
        this.valorGasto = valorGasto;
    }

    public CategoriaDto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDto categoria) {
        this.categoria = categoria;
    }

    public PessoaDto getCredor() {
        return credor;
    }

    public void setCredor(PessoaDto credor) {
        this.credor = credor;
    }

    public SubcategoriaDto getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(SubcategoriaDto subcategoria) {
        this.subcategoria = subcategoria;
    }

    public LocalDate getDataDespesa() {
        return dataDespesa;
    }

    public void setDataDespesa(LocalDate dataDespesa) {
        this.dataDespesa = dataDespesa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
