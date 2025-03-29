package dev.fabriciosilva.controlefinanceiro.domain.contabanco;

import dev.fabriciosilva.controlefinanceiro.domain.contabanco.dto.ContaBancoCreateRequest;
import dev.fabriciosilva.controlefinanceiro.domain.contabanco.dto.ContaBancoResponse;
import dev.fabriciosilva.controlefinanceiro.domain.contabanco.dto.ContaBancoUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("contabancos")
public class ContaBancoRest {

    private final ContaBancoService contaBancoService;

    public ContaBancoRest(ContaBancoService contaBancoService) {
        this.contaBancoService = contaBancoService;
    }

    @GetMapping
    public ResponseEntity<Page<ContaBancoResponse>> find(@PageableDefault() Pageable paginacao) {
        Page<ContaBancoResponse> page = contaBancoService.findAll(paginacao);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<ContaBancoResponse> save(@RequestBody @Valid ContaBancoCreateRequest form, UriComponentsBuilder uriBuilder) {
        ContaBancoResponse contaBanco = contaBancoService.create(form);
        URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(contaBanco.getId()).toUri();
        return ResponseEntity.created(uri).body(contaBanco);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaBancoResponse> findById(@PathVariable Integer id) {
        ContaBancoResponse categoria = contaBancoService.findById(id);
        return ResponseEntity.ok(categoria);
    }

    @PutMapping
    public ResponseEntity<ContaBancoResponse> update(@RequestBody @Valid ContaBancoUpdateRequest form) {
        return ResponseEntity.ok(contaBancoService.update(form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ContaBancoResponse> delete(@PathVariable Integer id) {
        contaBancoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
