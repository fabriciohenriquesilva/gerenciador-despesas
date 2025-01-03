package dev.fabriciosilva.controlefinanceiro.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecursoInexistenteException extends RuntimeException {

    public RecursoInexistenteException(Number id, String name) {
        super("Não foi encontrado um(a) " + name + " com id [" + id + "]");
    }

    public RecursoInexistenteException(Number id) {
        super("Não foi encontrado um registro com id [" + id + "]");
    }

    public RecursoInexistenteException(String nome) {
        super("Não foi encontrado um registro com nome/descrição: [" + nome + "]");
    }
}
