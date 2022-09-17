package dev.fabriciosilva.gerenciadordespesas.request;

import dev.fabriciosilva.gerenciadordespesas.domain.Despesa;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

public class DespesaPutRequestForm {

    private String id;
    @NotBlank(message = "É obrigatório preencher o campo descrição")
    private String descricao;
    @NotBlank(message = "É obrigatório preencher o campo valor gasto com um valor númerico")
    private String valorGasto;
    @NotBlank(message = "É obrigatório preencher o campo de data da despesa")
    private String dataDespesa;

    private String categoria;

    public String getDescricao() {
        return descricao;
    }

    public String getValorGasto() {
        return valorGasto;
    }

    public String getDataDespesa() {
        return dataDespesa;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValorGasto(String valorGasto) {
        this.valorGasto = valorGasto;
    }

    public void setDataDespesa(String dataDespesa) {
        this.dataDespesa = dataDespesa;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Despesa toDespesa() {
        Despesa despesa = new Despesa();
        despesa.setId(Integer.valueOf(this.id));
        despesa.setDescricao(descricao);
        despesa.setValorGasto(new BigDecimal(valorGasto));

        LocalDate data = LocalDate.parse(dataDespesa);
        despesa.setDataDespesa(data);

        return despesa;
    }
}
