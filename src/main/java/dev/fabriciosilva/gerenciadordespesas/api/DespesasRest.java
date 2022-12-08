package dev.fabriciosilva.gerenciadordespesas.api;

import dev.fabriciosilva.gerenciadordespesas.domain.Despesa;
import dev.fabriciosilva.gerenciadordespesas.dto.DespesaDto;
import dev.fabriciosilva.gerenciadordespesas.request.DespesaPostRequestForm;
import dev.fabriciosilva.gerenciadordespesas.request.DespesaPutRequestForm;
import dev.fabriciosilva.gerenciadordespesas.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/despesas")
public class DespesasRest {

    @Autowired
    private DespesaService despesaService;

    @GetMapping
    public ResponseEntity<Page<DespesaDto>> listarTodos(@PageableDefault() Pageable paginacao){
        Page<Despesa> despesas = despesaService.listarTodos(paginacao);

        return ResponseEntity.ok(DespesaDto.converterLista(despesas));
    }

    @PostMapping
    public ResponseEntity<DespesaDto> cadastrar(@RequestBody @Valid DespesaPostRequestForm despesaPostRequestForm, UriComponentsBuilder uriBuilder) {
        Despesa despesa = this.despesaService.novo(despesaPostRequestForm);
        URI uri = uriBuilder.path("/api/despesas/{id}").buildAndExpand(despesa.getId()).toUri();
        return ResponseEntity.created(uri).body(new DespesaDto(despesa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DespesaDto> detalhar(@PathVariable Integer id) {
        DespesaDto despesaDto = this.despesaService.detalhar(id);
        return ResponseEntity.ok(despesaDto);
    }

    @PutMapping
    public ResponseEntity<DespesaPutRequestForm> atualizar(@RequestBody @Valid DespesaPutRequestForm form) {
        this.despesaService.editar(form);
        return ResponseEntity.ok(form);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        this.despesaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
