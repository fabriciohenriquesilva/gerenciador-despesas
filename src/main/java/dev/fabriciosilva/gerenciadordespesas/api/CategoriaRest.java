package dev.fabriciosilva.gerenciadordespesas.api;

import dev.fabriciosilva.gerenciadordespesas.domain.Categoria;
import dev.fabriciosilva.gerenciadordespesas.dto.CategoriaDto;
import dev.fabriciosilva.gerenciadordespesas.repository.CategoriaRepository;
import dev.fabriciosilva.gerenciadordespesas.request.CategoriaForm;
import dev.fabriciosilva.gerenciadordespesas.service.CategoriaService;
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
@RequestMapping("api/categorias")
public class CategoriaRest {

    @Autowired
    private CategoriaRepository repository;

    @Autowired
    private CategoriaService service;

    @GetMapping
    public ResponseEntity<Page<CategoriaDto>> listar(@PageableDefault() Pageable paginacao) {
        Page<CategoriaDto> page = repository.findAll(paginacao)
                .map(CategoriaDto::new);

        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody CategoriaForm form, UriComponentsBuilder uriBuilder) {
        Categoria categoria = repository.save(new Categoria(form));
        URI uri = uriBuilder.path("/api/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        Categoria categoria = repository.findById(id).get();
        return ResponseEntity.ok(new CategoriaDto(categoria));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody CategoriaDto form) {
        service.edit(form);
        return ResponseEntity.ok(form);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
