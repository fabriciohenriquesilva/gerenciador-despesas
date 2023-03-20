package dev.fabriciosilva.gerenciadordespesas.api;

import dev.fabriciosilva.gerenciadordespesas.domain.Subcategoria;
import dev.fabriciosilva.gerenciadordespesas.dto.SubcategoriaDto;
import dev.fabriciosilva.gerenciadordespesas.repository.SubcategoriaRepository;
import dev.fabriciosilva.gerenciadordespesas.request.SubcategoriaForm;
import dev.fabriciosilva.gerenciadordespesas.service.SubcategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/subcategorias")
public class SubcategoriaRest implements CrudController<Long, SubcategoriaDto, SubcategoriaForm> {

    @Autowired
    private SubcategoriaRepository repository;

    @Autowired
    private SubcategoriaService service;

    @GetMapping
    public ResponseEntity<Page<SubcategoriaDto>> listar(@PageableDefault Pageable paginacao) {
        Page<SubcategoriaDto> page = repository.findAll(paginacao)
                .map(SubcategoriaDto::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping(params = "categoria")
    public List<SubcategoriaDto> listarPorCategoria(@RequestParam Long categoria) {
        return repository.findByCategoria(categoria).stream()
                .map(SubcategoriaDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody SubcategoriaForm form, UriComponentsBuilder uriBuilder) {
        Subcategoria subcategoria = service.save(form);
        URI uri = uriBuilder.path("/api/subcategorias/{id}").buildAndExpand(subcategoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new SubcategoriaDto(subcategoria));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        Optional<Subcategoria> optional = repository.findById(id);
        return ResponseEntity.ok(optional.orElseThrow());
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody SubcategoriaDto form) {
        service.edit(form);
        return ResponseEntity.ok(form);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
