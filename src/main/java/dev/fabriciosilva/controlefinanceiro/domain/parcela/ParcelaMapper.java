package dev.fabriciosilva.controlefinanceiro.domain.parcela;

import dev.fabriciosilva.controlefinanceiro.core.MapperContract;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ParcelaMapper implements MapperContract<Parcela, ParcelaDTO> {

    @Override
    public Parcela toEntity(ParcelaDTO dto) {
        if (dto == null) {
            return null;
        }

        Parcela parcela = new Parcela();
        BeanUtils.copyProperties(dto, parcela);

        return parcela;
    }

    @Override
    public ParcelaDTO toDTO(Parcela entity) {
        ParcelaDTO dto = new ParcelaDTO();
        BeanUtils.copyProperties(entity, dto);

        return dto;
    }
}
