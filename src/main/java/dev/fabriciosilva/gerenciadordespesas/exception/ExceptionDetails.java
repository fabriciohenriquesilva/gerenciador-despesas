package dev.fabriciosilva.gerenciadordespesas.exception;

import java.time.LocalDateTime;

public class ExceptionDetails {
    protected String titulo;
    protected int status;
    protected String detalhes;
    protected String developerMessage;
    protected LocalDateTime timestamp;

    public ExceptionDetails() {
    }

    public ExceptionDetails(String titulo, int status, String detalhes, String developerMessage, LocalDateTime timestamp) {
        this.titulo = titulo;
        this.status = status;
        this.detalhes = detalhes;
        this.developerMessage = developerMessage;
        this.timestamp = timestamp;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
