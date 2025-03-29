package dev.fabriciosilva.controlefinanceiro.domain.contabanco;

import dev.fabriciosilva.controlefinanceiro.domain.contabanco.dto.ContaBancoCreateRequest;
import dev.fabriciosilva.controlefinanceiro.domain.contabanco.dto.ContaBancoResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ContaBancoMapper {

    public ContaBancoResponse toDTO(ContaBanco contaBanco) {
        if (contaBanco == null) {
            return null;
        }

        ContaBancoResponse contaBancoResponse = new ContaBancoResponse();
        BeanUtils.copyProperties(contaBanco, contaBancoResponse);
        return contaBancoResponse;
    }

    public ContaBanco toEntity(ContaBancoCreateRequest form) {
        ContaBanco contaBanco = new ContaBanco();
        BeanUtils.copyProperties(form, contaBanco);
        return contaBanco;
    }

}
