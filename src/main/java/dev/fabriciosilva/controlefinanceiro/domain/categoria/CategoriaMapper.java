package dev.fabriciosilva.controlefinanceiro.domain.categoria;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaMapper {

    private final CategoriaRepository categoriaRepository;

    public CategoriaMapper(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaDTO toDTO(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getId());
        dto.setNome(categoria.getNome());

        if (categoria.getPai() != null) {
            dto.setPai(this.toDTO(categoria.getPai()));
        }

        return dto;
    }

    public Categoria toEntity(CategoriaDTO dto) {
        if (dto != null) {
            Categoria categoria = new Categoria();
            categoria.setId(dto.getId());
            categoria.setNome(dto.getNome());

            if (dto.getPai() != null) {
                Optional<Categoria> optional = categoriaRepository.findById(dto.getPai().getId());
                optional.ifPresent(categoria::setPai);
            }

            return categoria;
        }
        return null;
    }

}
