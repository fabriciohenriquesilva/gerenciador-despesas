package dev.fabriciosilva.controlefinanceiro.domain.categoria;

import dev.fabriciosilva.controlefinanceiro.core.ServiceContract;
import dev.fabriciosilva.controlefinanceiro.infra.exception.RecursoInexistenteException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional()
public class CategoriaService implements ServiceContract<CategoriaDTO, Integer> {

    private final CategoriaRepository repository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaService(CategoriaRepository repository, CategoriaMapper categoriaMapper) {
        this.repository = repository;
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    public Page<CategoriaDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(categoriaMapper::toDTO);
    }

    @Override
    public CategoriaDTO save(CategoriaDTO form) {
        Categoria categoria = categoriaMapper.toEntity(form);
        return categoriaMapper.toDTO(repository.save(categoria));
    }

    @Override
    public CategoriaDTO findById(Integer id) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new RecursoInexistenteException(id, "categoria"));

        return categoriaMapper.toDTO(categoria);
    }

    @Override
    public CategoriaDTO update(CategoriaDTO dto) {
        Categoria categoria = repository.findById(dto.getId())
                .orElseThrow(() -> new RecursoInexistenteException(dto.getId(), "categoria"));

        BeanUtils.copyProperties(dto, categoria);

        if (dto.getPai() != null) {
            categoria.setPai(repository.findById(dto.getPai().getId()).orElse(null));
        } else {
            categoria.setPai(null);
        }

        return categoriaMapper.toDTO(repository.save(categoria));
    }

    @Override
    public void delete(Integer id) {
        boolean existe = repository.existsById(id);
        if (!existe) {
            throw new RecursoInexistenteException(id, "categoria");
        }
        repository.deleteById(id);
    }

}
