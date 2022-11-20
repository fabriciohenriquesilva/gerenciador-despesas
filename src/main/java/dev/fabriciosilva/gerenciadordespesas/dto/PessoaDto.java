package dev.fabriciosilva.gerenciadordespesas.dto;

import dev.fabriciosilva.gerenciadordespesas.domain.Pessoa;

public class PessoaDto {

    private String id;
    private String nome;
    private String tipoPessoa;
    private String tipoDocumento;
    private String codigoDocumento;

    private String dataCriacao;

    public PessoaDto() {
    }

    public PessoaDto(Pessoa pessoa) {
        this.id = pessoa.getId().toString();
        this.nome = pessoa.getNome();
        this.tipoPessoa = pessoa.getTipoPessoa().name();
        this.tipoDocumento = pessoa.getDocumento().getTipoDocumento().name();
        this.codigoDocumento = pessoa.getDocumento().getCodigo();
        this.dataCriacao = pessoa.getDataCriacao().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
