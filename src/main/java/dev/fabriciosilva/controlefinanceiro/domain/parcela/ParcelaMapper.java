package dev.fabriciosilva.controlefinanceiro.domain.parcela;

import dev.fabriciosilva.controlefinanceiro.domain.contabanco.ContaBancoMapper;
import dev.fabriciosilva.controlefinanceiro.domain.contabanco.dto.ContaBancoResponse;
import dev.fabriciosilva.controlefinanceiro.domain.parcela.dto.ParcelaResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ParcelaMapper {

    private final ContaBancoMapper contaBancoMapper;

    public ParcelaMapper(ContaBancoMapper contaBancoMapper) {
        this.contaBancoMapper = contaBancoMapper;
    }

    public Parcela toEntity(ParcelaResponse dto) {
        if (dto == null) {
            return null;
        }

        Parcela parcela = new Parcela();
        BeanUtils.copyProperties(dto, parcela);

        return parcela;
    }

    public ParcelaResponse toDTO(Parcela entity) {
        ParcelaResponse dto = new ParcelaResponse();
        BeanUtils.copyProperties(entity, dto);

        ContaBancoResponse contaBanco = contaBancoMapper.toDTO(entity.getContaBanco());
        dto.setContaBanco(contaBanco);

        return dto;
    }
}
