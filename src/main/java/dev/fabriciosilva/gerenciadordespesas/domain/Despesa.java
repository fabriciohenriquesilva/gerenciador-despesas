package dev.fabriciosilva.gerenciadordespesas.domain;

import dev.fabriciosilva.gerenciadordespesas.request.DespesaPutRequestForm;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategoria_id", referencedColumnName = "id")
    private Subcategoria subcategoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credor_id", referencedColumnName = "id")
    private Pessoa credor;

    public Despesa() {
    }

    public Despesa(DespesaPutRequestForm form) {
        this.setId(Integer.valueOf(form.getId()));
        this.setDescricao(form.getDescricao());
        this.setValorGasto(new BigDecimal(form.getValorGasto().replace(",", ".")));

        LocalDate data = LocalDate.parse(form.getDataDespesa());
        this.setDataDespesa(data);
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    public Pessoa getCredor() {
        return credor;
    }

    public void setCredor(Pessoa credor) {
        this.credor = credor;
    }

}
