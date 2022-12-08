package dev.fabriciosilva.gerenciadordespesas.handler;

import dev.fabriciosilva.gerenciadordespesas.exception.ExceptionDetails;
import dev.fabriciosilva.gerenciadordespesas.exception.FormValidationExceptionDetails;
import dev.fabriciosilva.gerenciadordespesas.exception.RecursoInexistenteException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        String camposInvalidos = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String mensagemDeValidacaoDosCamposInvalidos = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                new FormValidationExceptionDetails(
                        "Campos inválidos",
                        HttpStatus.BAD_REQUEST.value(),
                        "Verique o preenchimento dos campos abaixo",
                        ex.getClass().getName(),
                        LocalDateTime.now(),
                        camposInvalidos,
                        mensagemDeValidacaoDosCamposInvalidos),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(RecursoInexistenteException.class)
    private ResponseEntity<Object> handleRecursoInexistente(RecursoInexistenteException ex) {
        return new ResponseEntity<>(
                new ExceptionDetails(
                        "O recurso solicitado não existe",
                        HttpStatus.NOT_FOUND.value(),
                        ex.getLocalizedMessage(),
                        ex.getClass().getSimpleName(),
                        LocalDateTime.now()),
                HttpStatus.NOT_FOUND
        );
    }


}
