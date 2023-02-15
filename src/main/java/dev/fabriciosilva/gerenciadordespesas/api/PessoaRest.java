package dev.fabriciosilva.gerenciadordespesas.api;

import dev.fabriciosilva.gerenciadordespesas.dto.PessoaDto;
import dev.fabriciosilva.gerenciadordespesas.request.PessoaRequestForm;
import dev.fabriciosilva.gerenciadordespesas.service.PessoaService;
import dev.fabriciosilva.gerenciadordespesas.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaRest {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ReportService reportService;

    @GetMapping
    public ResponseEntity<Page<PessoaDto>> listarTodos(@PageableDefault() Pageable paginacao) {
        Page<PessoaDto> pessoasDto = pessoaService.listarTodos(paginacao);
        return ResponseEntity.ok(pessoasDto);
    }

    @PostMapping
    public ResponseEntity<PessoaDto> cadastrar(@RequestBody @Valid PessoaRequestForm form, UriComponentsBuilder uriBuilder) {
        PessoaDto pessoaDto = pessoaService.novo(form);
        URI uri = uriBuilder.path("/api/pessoas/{id}").buildAndExpand(pessoaDto.getId()).toUri();

        return ResponseEntity.created(uri).body(pessoaDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDto> detalhar(@PathVariable Long id) {
        PessoaDto pessoaDto = pessoaService.buscarPorId(id);
        return ResponseEntity.ok(pessoaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDto> atualizar(@PathVariable Long id, @RequestBody @Valid PessoaRequestForm form) {
        return ResponseEntity.ok(pessoaService.atualizar(id, form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remover(@PathVariable Long id) {
        pessoaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> relatorio() throws JRException, SQLException {

        String query = "SELECT p.id, p.nome, p.tipo_pessoa, p.tipo_documento, p.codigo FROM pessoa p";

        String nomeArquivo = "src/main/resources/reports/pessoa/pessoas.jrxml";

        byte[] relatorio = this.reportService.gerarRelatorioEmPdf(query, nomeArquivo);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=pessoas.pdf");

        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(relatorio);
    }

}
