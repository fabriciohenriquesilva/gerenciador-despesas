package dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("movimentofinanceiro")
public class MovimentoFinanceiroRest {

    private final MovimentoFinanceiroService service;

    public MovimentoFinanceiroRest(MovimentoFinanceiroService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<MovimentoFinanceiroDTO>> findAll(@PageableDefault() Pageable paginacao) {
        Page<MovimentoFinanceiroDTO> page = service.findAll(paginacao);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<MovimentoFinanceiroDTO> save(@RequestBody @Valid MovimentoFinanceiroDTO form, UriComponentsBuilder uriBuilder) {
        MovimentoFinanceiroDTO categoria = service.save(form);
        URI uri = uriBuilder.path("/movimentofinanceiro/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(categoria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentoFinanceiroDTO> findById(@PathVariable Integer id) {
        MovimentoFinanceiroDTO categoria = service.findById(id);
        return ResponseEntity.ok(categoria);
    }

    @PutMapping
    public ResponseEntity<MovimentoFinanceiroDTO> update(@RequestBody @Valid MovimentoFinanceiroDTO form) {
        return ResponseEntity.ok(service.update(form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MovimentoFinanceiroDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
