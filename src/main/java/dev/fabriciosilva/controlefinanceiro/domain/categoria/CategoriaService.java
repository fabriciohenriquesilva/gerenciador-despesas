package dev.fabriciosilva.controlefinanceiro.domain.categoria;

import dev.fabriciosilva.controlefinanceiro.infra.exception.RecursoInexistenteException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional()
public class CategoriaService {

    private final CategoriaRepository repository;
    private final CategoriaDTOMapper categoriaDTOMapper;

    public CategoriaService(CategoriaRepository repository, CategoriaDTOMapper categoriaDTOMapper) {
        this.repository = repository;
        this.categoriaDTOMapper = categoriaDTOMapper;
    }

    public Page<CategoriaDTO> find(Pageable pageable) {
        return repository.findAll(pageable).map(categoriaDTOMapper);
    }

    public CategoriaDTO save(CategoriaDTO form) {
        Categoria categoria = toCategoria(form);

        categoria.setProcessamento(LocalDate.now());
        categoria.setPai(toCategoria(form.getPai()));
        categoria = repository.save(categoria);

        return categoriaDTOMapper.apply(categoria);
    }

    public CategoriaDTO findById(Integer id) {
        Optional<Categoria> optional = repository.findById(id);

        if (optional.isPresent()) {
            return categoriaDTOMapper.apply(optional.get());
        }
        throw new RecursoInexistenteException(id, "categoria");
    }

    public CategoriaDTO update(CategoriaDTO dto) {
        boolean existe = repository.existsById(dto.getId());

        if (existe) {
            Categoria categoria = toCategoria(dto);
            categoria.setAtualizacao(LocalDate.now());
            return categoriaDTOMapper.apply(repository.save(categoria));
        }

        throw new RecursoInexistenteException(dto.getId(), "categoria");
    }

    public void delete(Integer id) {
        boolean existe = repository.existsById(id);
        if (!existe) {
            throw new RecursoInexistenteException(id, "categoria");
        }
        repository.deleteById(id);
    }

    private Categoria toCategoria(CategoriaDTO dto) {
        if (dto != null) {
            Categoria categoria = new Categoria();
            categoria.setId(dto.getId());
            categoria.setNome(dto.getNome());

            if (dto.getPai() != null) {
                Optional<Categoria> optional = repository.findById(dto.getPai().getId());
                optional.ifPresent(categoria::setPai);
            }

            return categoria;
        }
        return null;
    }
}
