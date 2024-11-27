package dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro;

import dev.fabriciosilva.controlefinanceiro.core.ServiceContract;
import dev.fabriciosilva.controlefinanceiro.domain.categoria.CategoriaMapper;
import dev.fabriciosilva.controlefinanceiro.domain.categoria.CategoriaService;
import dev.fabriciosilva.controlefinanceiro.domain.parcela.Parcela;
import dev.fabriciosilva.controlefinanceiro.domain.parcela.ParcelaService;
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
public class MovimentoFinanceiroService implements ServiceContract<MovimentoFinanceiroDTO, Integer> {

    private final MovimentoFinanceiroRepository repository;
    private final ParcelaService parcelaService;
    private final MovimentoFinanceiroMapper mapper;
    private final CategoriaMapper categoriaMapper;
    private final CategoriaService categoriaService;

    public MovimentoFinanceiroService(MovimentoFinanceiroRepository repository, ParcelaService parcelaService, MovimentoFinanceiroMapper mapper, CategoriaMapper categoriaMapper, CategoriaService categoriaService) {
        this.repository = repository;
        this.parcelaService = parcelaService;
        this.mapper = mapper;
        this.categoriaMapper = categoriaMapper;
        this.categoriaService = categoriaService;
    }

    @Override
    public Page<MovimentoFinanceiroDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    @Override
    public MovimentoFinanceiroDTO save(MovimentoFinanceiroDTO form) {
        MovimentoFinanceiro movimentoFinanceiro = mapper.toEntity(form);

        movimentoFinanceiro = repository.save(movimentoFinanceiro);

        this.gerarParcelas(form, movimentoFinanceiro);

        return mapper.toDTO(movimentoFinanceiro);
    }

    @Override
    public MovimentoFinanceiroDTO findById(Integer id) {
        MovimentoFinanceiro movimentoFinanceiro = repository.findById(id)
                .orElseThrow(() -> new RecursoInexistenteException(id, "movimento financeiro"));

        return mapper.toDTOWithLists(movimentoFinanceiro);
    }

    @Override
    public MovimentoFinanceiroDTO update(MovimentoFinanceiroDTO dto) {
        MovimentoFinanceiro movimentoFinanceiro = repository.findById(dto.getId())
                .orElseThrow(() -> new RecursoInexistenteException(dto.getId(), "movimento financeiro"));

        BeanUtils.copyProperties(dto, movimentoFinanceiro);

        movimentoFinanceiro.setCategoria(categoriaMapper.toEntity(categoriaService.findById(dto.getCategoria().getId())));

        // TODO ver como ficará a alteração de dados da parcela caso seja alterado a quantidade, valor e data

        return mapper.toDTO(repository.save(movimentoFinanceiro));
    }

    @Override
    public void delete(Integer id) {
        boolean existe = repository.existsById(id);
        if (!existe) {
            throw new RecursoInexistenteException(id, "movimento financeiro");
        }
        repository.deleteById(id);
    }

    private void gerarParcelas(MovimentoFinanceiroDTO form, MovimentoFinanceiro movimentoFinanceiro) {

        Integer quantidadeParcelas = form.getQuantidadeParcelas();
        BigDecimal valorMovimentacao = form.getValor();

        for (int i = 0; i < quantidadeParcelas; i++) {
            Parcela parcela = new Parcela();

            parcela.setMovimentoFinanceiro(movimentoFinanceiro);
            parcela.setNumero(i + 1);
            parcela.setQuantidade(quantidadeParcelas);
            parcela.setValorTotal(valorMovimentacao.divide(BigDecimal.valueOf(quantidadeParcelas), RoundingMode.HALF_EVEN));
            parcela.setDataVencimento(LocalDate.now().plusMonths(i + 1));

            parcelaService.save(parcela);
        }
    }
}
