package dev.fabriciosilva.gerenciadordespesas.exception;

import java.time.LocalDateTime;

public class FormValidationExceptionDetails extends ExceptionDetails {

    private String campos;
    private String mensagemDeValidacao;

    public FormValidationExceptionDetails() {
    }

    public FormValidationExceptionDetails(String titulo, int status, String detalhes, String developerMessage,
                                          LocalDateTime timestamp, String campos, String mensagemDeValidacao) {
        super(titulo, status, detalhes, developerMessage, timestamp);
        this.campos = campos;
        this.mensagemDeValidacao = mensagemDeValidacao;
    }

    public String getCampos() {
        return campos;
    }

    public void setCampos(String campos) {
        this.campos = campos;
    }

    public String getMensagemDeValidacao() {
        return mensagemDeValidacao;
    }

    public void setMensagemDeValidacao(String mensagemDeValidacao) {
        this.mensagemDeValidacao = mensagemDeValidacao;
    }
}
