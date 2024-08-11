package dev.fabriciosilva.controlefinanceiro.domain.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("categorias")
public class CategoriaRest {

    @Autowired
    private CategoriaService service;

    @GetMapping
    public ResponseEntity<Page<CategoriaDto>> find(@PageableDefault() Pageable paginacao) {
        Page<CategoriaDto> page = service.find(paginacao);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaDto> save(@RequestBody CategoriaDto form, UriComponentsBuilder uriBuilder) {
        CategoriaDto categoria = service.save(form);
        URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(categoria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> findById(@PathVariable Integer id) {
        CategoriaDto categoria = service.findById(id);
        return ResponseEntity.ok(categoria);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<CategoriaDto> update(@RequestBody CategoriaDto form) {
        service.update(form);
        return ResponseEntity.ok(form);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoriaDto> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
