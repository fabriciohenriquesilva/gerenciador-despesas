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

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "subcategoria_id", referencedColumnName = "id")
    private Subcategoria subcategoria;

    @ManyToOne
    @JoinColumn(name = "credor_id", referencedColumnName = "id")
    private Pessoa credor;

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

    public DespesaPutRequestForm toDespesaDto() {
        DespesaPutRequestForm despesaPutRequestForm = new DespesaPutRequestForm();
        despesaPutRequestForm.setId(String.valueOf(this.id));
        despesaPutRequestForm.setDescricao(this.descricao);
        despesaPutRequestForm.setValorGasto(String.valueOf(this.valorGasto));
        despesaPutRequestForm.setDataDespesa(String.valueOf(this.dataDespesa));
        despesaPutRequestForm.setCategoria(this.categoria != null ? this.categoria.getNome() : "0");
        despesaPutRequestForm.setCredor(String.valueOf(this.credor != null ? this.credor.getId() : 0));
        despesaPutRequestForm.setSubcategoria(this.subcategoria != null ? this.subcategoria.getNome() : "0");

        return despesaPutRequestForm;
    }
}
