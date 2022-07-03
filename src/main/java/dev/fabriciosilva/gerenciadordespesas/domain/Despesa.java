package dev.fabriciosilva.gerenciadordespesas.domain;

import dev.fabriciosilva.gerenciadordespesas.request.DespesaRequestForm;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "despesa")
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    private BigDecimal valorGasto;
    private LocalDate dataDespesa;

    public Despesa() {
    }

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

    public BigDecimal getValorGasto() {
        return valorGasto;
    }

    public void setValorGasto(BigDecimal valorGasto) {
        this.valorGasto = valorGasto;
    }

    public LocalDate getDataDespesa() {
        return dataDespesa;
    }

    public void setDataDespesa(LocalDate dataDespesa) {
        this.dataDespesa = dataDespesa;
    }

    public DespesaRequestForm toDespesaDto(){
        DespesaRequestForm despesaRequestForm = new DespesaRequestForm();
        despesaRequestForm.setId(String.valueOf(this.id));
        despesaRequestForm.setDescricao(this.descricao);
        despesaRequestForm.setValorGasto(String.valueOf(this.valorGasto));
        despesaRequestForm.setDataDespesa(String.valueOf(this.dataDespesa));

        return despesaRequestForm;
    }
}
