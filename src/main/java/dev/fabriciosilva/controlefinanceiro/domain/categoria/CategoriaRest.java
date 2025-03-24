package dev.fabriciosilva.controlefinanceiro.domain.categoria;

import dev.fabriciosilva.controlefinanceiro.domain.categoria.dto.CategoriaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("categorias")
public class CategoriaRest {

    private final CategoriaService service;

    public CategoriaRest(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<CategoriaDTO>> find(@PageableDefault() Pageable paginacao) {
        Page<CategoriaDTO> page = service.findAll(paginacao);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> save(@RequestBody @Valid CategoriaDTO form, UriComponentsBuilder uriBuilder) {
        CategoriaDTO categoria = service.create(form);
        URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(categoria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable Integer id) {
        CategoriaDTO categoria = service.findById(id);
        return ResponseEntity.ok(categoria);
    }

    @PutMapping
    public ResponseEntity<CategoriaDTO> update(@RequestBody @Valid CategoriaDTO form) {
        return ResponseEntity.ok(service.update(form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoriaDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
