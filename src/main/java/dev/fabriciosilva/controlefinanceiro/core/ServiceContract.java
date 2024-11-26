package dev.fabriciosilva.controlefinanceiro.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceContract<DTO, ID> {

    Page<DTO> findAll(Pageable pageable);

    DTO save(DTO form);

    DTO findById(ID id);

    DTO update(DTO form);

    void delete(ID id);

}
