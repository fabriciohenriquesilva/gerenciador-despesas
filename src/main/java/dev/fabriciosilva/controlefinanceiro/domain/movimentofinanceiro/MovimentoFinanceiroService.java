package dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro;

import dev.fabriciosilva.controlefinanceiro.domain.categoria.Categoria;
import dev.fabriciosilva.controlefinanceiro.domain.categoria.CategoriaService;
import dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro.dto.MovimentoFinanceiroCreateRequest;
import dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro.dto.MovimentoFinanceiroDetailResponse;
import dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro.dto.MovimentoFinanceiroSummaryResponse;
import dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro.dto.MovimentoFinanceiroUpdateRequest;
import dev.fabriciosilva.controlefinanceiro.domain.parcela.Parcela;
import dev.fabriciosilva.controlefinanceiro.domain.parcela.ParcelaService;
import dev.fabriciosilva.controlefinanceiro.infra.exception.FormValidationException;
import dev.fabriciosilva.controlefinanceiro.infra.exception.RecursoInexistenteException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
@Transactional()
public class MovimentoFinanceiroService {

    private final MovimentoFinanceiroRepository repository;
    private final ParcelaService parcelaService;
    private final MovimentoFinanceiroMapper mapper;
    private final CategoriaService categoriaService;

    public MovimentoFinanceiroService(MovimentoFinanceiroRepository repository, ParcelaService parcelaService, MovimentoFinanceiroMapper mapper, CategoriaService categoriaService) {
        this.repository = repository;
        this.parcelaService = parcelaService;
        this.mapper = mapper;
        this.categoriaService = categoriaService;
    }

    public Page<MovimentoFinanceiroSummaryResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toSummaryResponse);
    }

    public MovimentoFinanceiroSummaryResponse save(MovimentoFinanceiroCreateRequest form) {
        MovimentoFinanceiro movimentoFinanceiro = mapper.toEntity(form);
        movimentoFinanceiro = repository.save(movimentoFinanceiro);

        this.gerarParcelas(movimentoFinanceiro, form.getQuantidadeParcelas(), form.getValor());

        return mapper.toSummaryResponse(movimentoFinanceiro);
    }

    public MovimentoFinanceiroDetailResponse findById(Integer id) {
        MovimentoFinanceiro movimentoFinanceiro = repository.findById(id)
                .orElseThrow(() -> new RecursoInexistenteException(id, "movimento financeiro"));

        return mapper.toDetailResponse(movimentoFinanceiro);
    }

    public MovimentoFinanceiroSummaryResponse update(MovimentoFinanceiroUpdateRequest form) {
        if (form.getId() == null) {
            throw new FormValidationException("Não foi informado o ID do registro a ser atualizado");
        }

        MovimentoFinanceiro movimentoFinanceiro = repository.findById(form.getId())
                .orElseThrow(() -> new RecursoInexistenteException(form.getId(), "movimento financeiro"));

        BeanUtils.copyProperties(form, movimentoFinanceiro);

        Categoria categoria = categoriaService.getById(form.getCategoria().getId());
        movimentoFinanceiro.setCategoria(categoria);

        MovimentoFinanceiro saved = repository.save(movimentoFinanceiro);

        // TODO ver como ficará a alteração de dados da parcela caso seja alterado a quantidade, valor e data

        return mapper.toSummaryResponse(saved);
    }

    public void delete(Integer id) {
        boolean existe = repository.existsById(id);
        if (!existe) {
            throw new RecursoInexistenteException(id, "movimento financeiro");
        }
        repository.deleteById(id);
    }

    private void gerarParcelas(MovimentoFinanceiro movimentoFinanceiro, Integer numeroParcelas, BigDecimal valor) {
        BigDecimal valorParcela = valor.divide(BigDecimal.valueOf(numeroParcelas), RoundingMode.HALF_DOWN);

        for (int i = 0; i < numeroParcelas; i++) {
            Parcela parcela = new Parcela();

            parcela.setMovimentoFinanceiro(movimentoFinanceiro);
            parcela.setNumero(i + 1);
            parcela.setQuantidade(numeroParcelas);
            parcela.setValorTotal(valorParcela);
            parcela.setDataVencimento(LocalDate.now().plusMonths(i + 1));

            parcelaService.save(parcela);
        }
    }
}
