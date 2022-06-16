package dev.fabriciosilva.gerenciadordespesas.dto;

import dev.fabriciosilva.gerenciadordespesas.domain.Despesa;

import java.time.LocalDate;

public class DespesaDto {

    private String descricao;
    private Double valorGasto;
    private String dataDespesa;

    public String getDescricao() {
        return descricao;
    }

    public Double getValorGasto() {
        return valorGasto;
    }

    public String getDataDespesa() {
        return dataDespesa;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValorGasto(Double valorGasto) {
        this.valorGasto = valorGasto;
    }

    public void setDataDespesa(String dataDespesa) {
        this.dataDespesa = dataDespesa;
    }

    public Despesa toDespesa(){
        Despesa despesa = new Despesa();
        despesa.setDescricao(descricao);
        despesa.setValorGasto(valorGasto);

        LocalDate data = LocalDate.parse(dataDespesa);
        despesa.setDataDespesa(data);

        return despesa;
    }
}
