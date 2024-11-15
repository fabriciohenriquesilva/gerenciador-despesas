package dev.fabriciosilva.controlefinanceiro.domain.categoria;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CategoriaDTOMapper implements Function<Categoria, CategoriaDTO> {

    @Override
    public CategoriaDTO apply(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getId());
        dto.setNome(categoria.getNome());

        if (categoria.getPai() != null) {
            dto.setPai(this.apply(categoria.getPai()));
        }

        return dto;
    }

}
