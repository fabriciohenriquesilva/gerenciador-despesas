package dev.fabriciosilva.controlefinanceiro.domain.parcela;

import dev.fabriciosilva.controlefinanceiro.domain.parcela.dto.ParcelaResponse;
import dev.fabriciosilva.controlefinanceiro.domain.parcela.dto.ParcelaUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("parcelas")
public class ParcelaRest {

    private final ParcelaService parcelaService;

    public ParcelaRest(ParcelaService parcelaService) {
        this.parcelaService = parcelaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParcelaResponse> findById(@PathVariable Integer id) {
        ParcelaResponse parcela = parcelaService.findById(id);
        return ResponseEntity.ok(parcela);
    }

    @PutMapping
    public ResponseEntity<ParcelaResponse> update(@RequestBody @Valid ParcelaUpdateRequest form) {
        return ResponseEntity.ok(parcelaService.update(form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        parcelaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
