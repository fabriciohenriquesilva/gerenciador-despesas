package dev.fabriciosilva.controlefinanceiro.core;

public interface MapperContract<T, DTO> {

    T toEntity(DTO dto);

    DTO toDTO(T entity);

}
