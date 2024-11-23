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
    private final CategoriaMapper categoriaMapper;

    public CategoriaService(CategoriaRepository repository, CategoriaMapper categoriaMapper) {
        this.repository = repository;
        this.categoriaMapper = categoriaMapper;
    }

    public Page<CategoriaDTO> find(Pageable pageable) {
        return repository.findAll(pageable).map(categoriaMapper::toDTO);
    }

    public CategoriaDTO save(CategoriaDTO form) {
        Categoria categoria = categoriaMapper.toEntity(form);

        categoria.setProcessamento(LocalDate.now());
        categoria = repository.save(categoria);

        return categoriaMapper.toDTO(categoria);
    }

    public CategoriaDTO findById(Integer id) {
        Optional<Categoria> optional = repository.findById(id);

        if (optional.isPresent()) {
            return categoriaMapper.toDTO(optional.get());
        }
        throw new RecursoInexistenteException(id, "categoria");
    }

    public CategoriaDTO update(CategoriaDTO dto) {
        boolean existe = repository.existsById(dto.getId());

        if (existe) {
            Categoria categoria = categoriaMapper.toEntity(dto);
            categoria.setAtualizacao(LocalDate.now());
            return categoriaMapper.toDTO(repository.save(categoria));
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

}
