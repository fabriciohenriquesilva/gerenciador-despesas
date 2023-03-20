package dev.fabriciosilva.gerenciadordespesas.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface CrudController<ID, DTO, FORM> {

    ResponseEntity<Page<DTO>> listar(Pageable paginacao);
    ResponseEntity cadastrar(FORM form, UriComponentsBuilder uriBuilder);
    ResponseEntity detalhar(ID id);
    ResponseEntity atualizar(DTO form);
    ResponseEntity excluir(ID id);
}
