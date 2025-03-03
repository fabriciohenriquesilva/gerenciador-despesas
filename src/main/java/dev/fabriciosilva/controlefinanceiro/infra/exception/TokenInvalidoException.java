package dev.fabriciosilva.controlefinanceiro.infra.exception;

public class TokenInvalidoException extends RuntimeException {

    private String message;

    public TokenInvalidoException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
