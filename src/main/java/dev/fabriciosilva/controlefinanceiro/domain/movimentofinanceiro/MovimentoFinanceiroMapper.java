package dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro;

import dev.fabriciosilva.controlefinanceiro.domain.categoria.CategoriaMapper;
import dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro.dto.MovimentoFinanceiroCreateRequest;
import dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro.dto.MovimentoFinanceiroResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MovimentoFinanceiroMapper {

    private final CategoriaMapper categoriaMapper;

    public MovimentoFinanceiroMapper(CategoriaMapper categoriaMapper) {
        this.categoriaMapper = categoriaMapper;
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

    public MovimentoFinanceiroResponse toDTO(MovimentoFinanceiro entity) {
        MovimentoFinanceiroResponse dto = new MovimentoFinanceiroResponse();
        BeanUtils.copyProperties(entity, dto);

        dto.setCategoria(categoriaMapper.toDTO(entity.getCategoria()));

        return dto;
    }
}
