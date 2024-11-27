package dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro;

import dev.fabriciosilva.controlefinanceiro.core.MapperContract;
import dev.fabriciosilva.controlefinanceiro.domain.categoria.CategoriaMapper;
import dev.fabriciosilva.controlefinanceiro.domain.parcela.ParcelaDTO;
import dev.fabriciosilva.controlefinanceiro.domain.parcela.ParcelaMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MovimentoFinanceiroMapper implements MapperContract<MovimentoFinanceiro, MovimentoFinanceiroDTO> {

    private final CategoriaMapper categoriaMapper;
    private final ParcelaMapper parcelaMapper;

    public MovimentoFinanceiroMapper(CategoriaMapper categoriaMapper, ParcelaMapper parcelaMapper) {
        this.categoriaMapper = categoriaMapper;
        this.parcelaMapper = parcelaMapper;
    }

    @Override
    public MovimentoFinanceiro toEntity(MovimentoFinanceiroDTO dto) {
        if (dto == null) {
            return null;
        }

        MovimentoFinanceiro movimentoFinanceiro = new MovimentoFinanceiro();
        BeanUtils.copyProperties(dto, movimentoFinanceiro);

        movimentoFinanceiro.setCategoria(categoriaMapper.toEntity(dto.getCategoria()));

        return movimentoFinanceiro;
    }

    @Override
    public MovimentoFinanceiroDTO toDTO(MovimentoFinanceiro entity) {
        MovimentoFinanceiroDTO dto = new MovimentoFinanceiroDTO();
        BeanUtils.copyProperties(entity, dto);

        dto.setCategoria(categoriaMapper.toDTO(entity.getCategoria()));

        return dto;
    }

    public MovimentoFinanceiroDTO toDTOWithLists(MovimentoFinanceiro entity) {
        MovimentoFinanceiroDTO movimentoFinanceiroDTO = toDTO(entity);

        entity.getParcelas().forEach(parcela -> {
            ParcelaDTO parcelaDTO = parcelaMapper.toDTO(parcela);
            parcelaDTO.setMovimentoFinanceiroDTO(movimentoFinanceiroDTO);
            movimentoFinanceiroDTO.addParcela(parcelaDTO);
        });

        return movimentoFinanceiroDTO;
    }
}
