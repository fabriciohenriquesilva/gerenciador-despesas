package dev.fabriciosilva.controlefinanceiro.domain.categoria;

import dev.fabriciosilva.controlefinanceiro.infra.exception.RecursoInexistenteException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public Page<CategoriaDto> find(Pageable pageable) {
        return repository.findAll(pageable).map(CategoriaDto::new);
    }

    public CategoriaDto save(CategoriaDto form) {
        Categoria categoria = repository.save(form.toCategoria());
        return new CategoriaDto(categoria);
    }

    public CategoriaDto findById(Integer id) {
        Optional<Categoria> optional = repository.findById(id);

        if (optional.isPresent()) {
            return new CategoriaDto(optional.get());
        }
        throw new RecursoInexistenteException("Não foi encontrado uma categoria com o id " + id);
    }

    public void update(CategoriaDto dto) {
        Optional<Categoria> optional = repository.findById(dto.getId());

        if (optional.isPresent()) {
            repository.save(dto.toCategoria());
        }
    }

    public void delete(Integer id) {
        boolean existe = repository.existsById(id);
        if (!existe) {
            throw new RecursoInexistenteException("Não foi encontrado uma categoria com o id " + id);
        }
        repository.deleteById(id);
    }
}
