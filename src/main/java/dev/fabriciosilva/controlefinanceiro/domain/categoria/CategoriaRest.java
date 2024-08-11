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
    private CategoriaRepository repository;

    @Autowired
    private CategoriaService service;

    @GetMapping
    public ResponseEntity<Page<CategoriaDto>> find(@PageableDefault() Pageable paginacao) {
        Page<CategoriaDto> page = repository.findAll(paginacao)
                .map(CategoriaDto::new);

        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaDto> save(@RequestBody CategoriaDto form, UriComponentsBuilder uriBuilder) {
        Categoria categoria = repository.save(form.toCategoria());
        URI uri = uriBuilder.path("/api/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> findById(@PathVariable Long id) {
        Categoria categoria = repository.findById(id).get();
        return ResponseEntity.ok(new CategoriaDto(categoria));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<CategoriaDto> update(@RequestBody CategoriaDto form) {
        service.edit(form);
        return ResponseEntity.ok(form);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoriaDto> delete(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
