package dev.fabriciosilva.controlefinanceiro.infra.exception;

public class TokenGenerationException extends RuntimeException {

    public TokenGenerationException(String message) {
        super(message);
    }
}
