package dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro;

import dev.fabriciosilva.controlefinanceiro.domain.categoria.CategoriaMapper;
import dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro.dto.MovimentoFinanceiroCreateRequest;
import dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro.dto.MovimentoFinanceiroDetailResponse;
import dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro.dto.MovimentoFinanceiroSummaryResponse;
import dev.fabriciosilva.controlefinanceiro.domain.parcela.ParcelaDTO;
import dev.fabriciosilva.controlefinanceiro.domain.parcela.ParcelaMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MovimentoFinanceiroMapper {

    private final CategoriaMapper categoriaMapper;
    private final ParcelaMapper parcelaMapper;

    public MovimentoFinanceiroMapper(CategoriaMapper categoriaMapper, ParcelaMapper parcelaMapper) {
        this.categoriaMapper = categoriaMapper;
        this.parcelaMapper = parcelaMapper;
    }

    public MovimentoFinanceiro toEntity(MovimentoFinanceiroCreateRequest dto) {
        if (dto == null) {
            return null;
        }

        MovimentoFinanceiro movimentoFinanceiro = new MovimentoFinanceiro();
        BeanUtils.copyProperties(dto, movimentoFinanceiro);
        movimentoFinanceiro.setCategoria(categoriaMapper.toEntity(dto.getCategoria()));

        return movimentoFinanceiro;
    }

    public MovimentoFinanceiroSummaryResponse toSummaryResponse(MovimentoFinanceiro entity) {
        MovimentoFinanceiroSummaryResponse dto = new MovimentoFinanceiroSummaryResponse();
        BeanUtils.copyProperties(entity, dto);

        dto.setCategoria(categoriaMapper.toDTO(entity.getCategoria()));

        return dto;
    }

    public MovimentoFinanceiroDetailResponse toDetailResponse(MovimentoFinanceiro entity) {
        MovimentoFinanceiroSummaryResponse summaryResponse = toSummaryResponse(entity);
        MovimentoFinanceiroDetailResponse detailResponse = new MovimentoFinanceiroDetailResponse();
        detailResponse.setMovimentoFinanceiro(summaryResponse);

        entity.getParcelas().forEach(parcela -> {
            ParcelaDTO parcelaDTO = parcelaMapper.toDTO(parcela);
            detailResponse.addParcela(parcelaDTO);
        });

        return detailResponse;
    }
}
