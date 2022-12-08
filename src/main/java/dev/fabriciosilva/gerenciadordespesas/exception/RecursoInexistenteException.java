package dev.fabriciosilva.gerenciadordespesas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecursoInexistenteException extends RuntimeException {

    public RecursoInexistenteException(String message) {
        super(message);
    }
}
