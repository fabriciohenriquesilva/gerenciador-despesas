package dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro;

import dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro.dto.MovimentoFinanceiroCreateRequest;
import dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro.dto.MovimentoFinanceiroDetailResponse;
import dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro.dto.MovimentoFinanceiroSummaryResponse;
import dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro.dto.MovimentoFinanceiroUpdateRequest;
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
    public ResponseEntity<Page<MovimentoFinanceiroSummaryResponse>> findAll(@PageableDefault() Pageable paginacao) {
        Page<MovimentoFinanceiroSummaryResponse> page = service.findAll(paginacao);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<MovimentoFinanceiroSummaryResponse> create(@RequestBody @Valid MovimentoFinanceiroCreateRequest form, UriComponentsBuilder uriBuilder) {
        MovimentoFinanceiroSummaryResponse movimentoFinanceiro = service.save(form);
        URI uri = uriBuilder.path("/movimentofinanceiro/{id}").buildAndExpand(movimentoFinanceiro.getId()).toUri();
        return ResponseEntity.created(uri).body(movimentoFinanceiro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentoFinanceiroDetailResponse> findById(@PathVariable Integer id) {
        MovimentoFinanceiroDetailResponse movimentoFinanceiro = service.findById(id);
        return ResponseEntity.ok(movimentoFinanceiro);
    }

    @PutMapping
    public ResponseEntity<MovimentoFinanceiroSummaryResponse> update(@RequestBody @Valid MovimentoFinanceiroUpdateRequest form) {
        return ResponseEntity.ok(service.update(form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
