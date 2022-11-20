package dev.fabriciosilva.gerenciadordespesas.request;

import dev.fabriciosilva.gerenciadordespesas.domain.Documento;
import dev.fabriciosilva.gerenciadordespesas.domain.Pessoa;
import dev.fabriciosilva.gerenciadordespesas.domain.TipoPessoa;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

public class PessoaRequestForm {

    @Valid
    @NotEmpty
    private String nome;

    @Valid
    @NotEmpty
    private String tipoPessoa;

    @Valid
    @NotEmpty
    private String tipoDocumento;

    @Valid
    @NotEmpty
    private String codigoDocumento;

    public Pessoa toPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(this.nome);
        pessoa.setDataCriacao(LocalDate.now());

        pessoa.setTipoPessoa(TipoPessoa.valueOf(this.tipoPessoa));

        Documento documento = new Documento();

        documento.setTipoDocumento(Documento.TipoDocumento.valueOf(this.tipoDocumento));
        documento.setCodigo(this.codigoDocumento);

        pessoa.setDocumento(documento);

        return pessoa;
    }

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
