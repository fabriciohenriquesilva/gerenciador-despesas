package dev.fabriciosilva.gerenciadordespesas.request;

import dev.fabriciosilva.gerenciadordespesas.domain.Despesa;

import javax.validation.constraints.NotBlank;

public class DespesaPutRequestForm {

    private String id;
    @NotBlank(message = "É obrigatório preencher o campo descrição")
    private String descricao;
    @NotBlank(message = "É obrigatório preencher o campo valor gasto com um valor númerico")
    private String valorGasto;
    @NotBlank(message = "É obrigatório preencher o campo de data da despesa")
    private String dataDespesa;

    @NotBlank(message = "É obrigatório informar o campo de categoria da despesa")
    private String categoria;

    @NotBlank(message = "É obrigatório informar o campo de credor da despesa")
    private String credorNome;

    @NotBlank(message = "É obrigatório informar o campo de credor da despesa")
    private String credorId;

    @NotBlank(message = "É obrigatório informar o campo de subcategoria da despesa")
    private String subcategoria;

    public DespesaPutRequestForm() { }

    public DespesaPutRequestForm(Despesa despesa) {
        this.id = despesa.getId().toString();
        this.descricao = despesa.getDescricao();
        this.valorGasto = despesa.getValorGasto().toString();
        this.dataDespesa = despesa.getDataDespesa().toString();
        this.categoria = despesa.getCategoria() != null ? despesa.getCategoria().getNome() : "0";
        this.subcategoria = despesa.getSubcategoria() != null ? despesa.getSubcategoria().getNome() : "0";
        this.credorNome = despesa.getCredor() != null ? despesa.getCredor().getNome() : "0";
        this.credorId = despesa.getCredor() != null ? despesa.getCredor().getId().toString() : "0";
    }

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

    public String getCredorNome() {
        return credorNome;
    }

    public void setCredorNome(String credorNome) {
        this.credorNome = credorNome;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    public String getCredorId() {
        return credorId;
    }

    public void setCredorId(String credorId) {
        this.credorId = credorId;
    }
}
