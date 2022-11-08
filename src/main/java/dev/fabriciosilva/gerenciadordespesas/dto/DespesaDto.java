package dev.fabriciosilva.gerenciadordespesas.dto;

import dev.fabriciosilva.gerenciadordespesas.domain.Despesa;
import org.springframework.data.domain.Page;

public class DespesaDto {

    private String descricao;
    private String valorGasto;
    private String dataDespesa;
    private String categoria;

    public DespesaDto(Despesa despesa) {
        this.descricao = despesa.getDescricao();
        this.valorGasto = despesa.getValorGasto().toString();
        this.dataDespesa = despesa.getDataDespesa().toString();
        this.categoria = despesa.getCategoria().getNome();
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

    public String getValorGasto() {
        return valorGasto;
    }

    public void setValorGasto(String valorGasto) {
        this.valorGasto = valorGasto;
    }

    public String getDataDespesa() {
        return dataDespesa;
    }

    public void setDataDespesa(String dataDespesa) {
        this.dataDespesa = dataDespesa;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
