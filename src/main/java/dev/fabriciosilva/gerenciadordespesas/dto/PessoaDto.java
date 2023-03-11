package dev.fabriciosilva.gerenciadordespesas.dto;

import dev.fabriciosilva.gerenciadordespesas.domain.Pessoa;

import java.time.LocalDate;

public class PessoaDto {

    private Long id;
    private String nome;
    private String tipoPessoa;
    private String tipoDocumento;
    private String codigoDocumento;
    private String mascaraDocumento;
    private LocalDate dataCriacao;

    public PessoaDto() {
    }

    public PessoaDto(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.tipoPessoa = pessoa.getTipoPessoa().name();
        this.tipoDocumento = pessoa.getDocumento().getTipoDocumento().name();
        this.mascaraDocumento = pessoa.getDocumento().getTipoDocumento().getMascara();
        this.codigoDocumento = pessoa.getDocumento().getCodigo();
        this.dataCriacao = pessoa.getDataCriacao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getMascaraDocumento() {
        return mascaraDocumento;
    }

    public void setMascaraDocumento(String mascaraDocumento) {
        this.mascaraDocumento = mascaraDocumento;
    }
}
