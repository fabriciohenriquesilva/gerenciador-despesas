package dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro;

import dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro.dto.MovimentoFinanceiroCreateRequest;
import dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro.dto.MovimentoFinanceiroResponse;
import dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro.dto.MovimentoFinanceiroUpdateRequest;
import dev.fabriciosilva.controlefinanceiro.domain.parcela.dto.ParcelaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("movimento-financeiro")
public class MovimentoFinanceiroRest {

    private final MovimentoFinanceiroService movimentoFinanceiroService;

    public MovimentoFinanceiroRest(MovimentoFinanceiroService movimentoFinanceiroService) {
        this.movimentoFinanceiroService = movimentoFinanceiroService;
    }

    @GetMapping()
    public ResponseEntity<Page<MovimentoFinanceiroResponse>> findAll(@PageableDefault() Pageable pageable) {
        Page<MovimentoFinanceiroResponse> page = movimentoFinanceiroService.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<MovimentoFinanceiroResponse> create(@RequestBody @Valid MovimentoFinanceiroCreateRequest form, UriComponentsBuilder uriBuilder) {
        MovimentoFinanceiroResponse movimentoFinanceiro = movimentoFinanceiroService.create(form);
        URI uri = uriBuilder.path("/movimentofinanceiro/{id}").buildAndExpand(movimentoFinanceiro.getId()).toUri();
        return ResponseEntity.created(uri).body(movimentoFinanceiro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentoFinanceiroResponse> findById(@PathVariable Integer id) {
        MovimentoFinanceiroResponse movimentoFinanceiro = movimentoFinanceiroService.findById(id);
        return ResponseEntity.ok(movimentoFinanceiro);
    }

    @PutMapping
    public ResponseEntity<MovimentoFinanceiroResponse> update(@RequestBody @Valid MovimentoFinanceiroUpdateRequest form) {
        return ResponseEntity.ok(movimentoFinanceiroService.update(form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        movimentoFinanceiroService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/parcelas")
    public ResponseEntity<List<ParcelaResponse>> getParcelas(@PathVariable Integer id) {
        List<ParcelaResponse> parcelas = this.movimentoFinanceiroService.getParcelas(id);
        return ResponseEntity.ok(parcelas);
    }

}
