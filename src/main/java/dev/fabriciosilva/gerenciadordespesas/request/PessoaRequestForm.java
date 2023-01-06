package dev.fabriciosilva.gerenciadordespesas.request;

import javax.validation.constraints.NotBlank;

public class PessoaRequestForm {

    @NotBlank
    private String nome;

    @NotBlank
    private String tipoPessoa;

    @NotBlank
    private String tipoDocumento;

    @NotBlank
    private String codigoDocumento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getCodigoDocumento() {
        return codigoDocumento;
    }

    public void setCodigoDocumento(String codigoDocumento) {
        this.codigoDocumento = codigoDocumento;
    }
}
