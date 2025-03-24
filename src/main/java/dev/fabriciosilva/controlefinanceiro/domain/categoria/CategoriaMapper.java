package dev.fabriciosilva.controlefinanceiro.domain.categoria;

import dev.fabriciosilva.controlefinanceiro.core.MapperContract;
import dev.fabriciosilva.controlefinanceiro.domain.categoria.dto.CategoriaDTO;
import dev.fabriciosilva.controlefinanceiro.infra.exception.RecursoInexistenteException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaMapper implements MapperContract<Categoria, CategoriaDTO> {

    private final CategoriaRepository categoriaRepository;

    public CategoriaMapper(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria toEntity(CategoriaDTO dto) {
        if (dto != null) {
            if (dto.getId() != null) {
                return this.categoriaRepository.findById(dto.getId())
                        .orElseThrow(() -> new RecursoInexistenteException(dto.getId(), "Categoria"));
            }

            Categoria categoria = new Categoria();
            BeanUtils.copyProperties(dto, categoria);

            if (dto.getPai() != null) {
                Optional<Categoria> optional = categoriaRepository.findById(dto.getPai().getId());
                optional.ifPresent(categoria::setPai);
            }

            return categoria;
        }
        return null;
    }

    @Override
    public CategoriaDTO toDTO(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        BeanUtils.copyProperties(categoria, dto);

        if (categoria.getPai() != null) {
            dto.setPai(this.toDTO(categoria.getPai()));
        }

        return dto;
    }

}
