package dev.fabriciosilva.controlefinanceiro.domain.categoria;

import dev.fabriciosilva.controlefinanceiro.core.AbstractService;
import dev.fabriciosilva.controlefinanceiro.domain.categoria.dto.CategoriaDTO;
import dev.fabriciosilva.controlefinanceiro.infra.context.AuthenticationFacade;
import dev.fabriciosilva.controlefinanceiro.infra.exception.RecursoInexistenteException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoriaService extends AbstractService<Categoria, Integer> {

    private final CategoriaRepository repository;
    private final CategoriaMapper categoriaMapper;
    private final AuthenticationFacade authenticationFacade;

    public CategoriaService(CategoriaRepository repository, CategoriaMapper categoriaMapper, AuthenticationFacade authenticationFacade) {
        this.repository = repository;
        this.categoriaMapper = categoriaMapper;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public CategoriaRepository getRepository() {
        return this.repository;
    }

    public Page<CategoriaDTO> findAll(Pageable pageable) {
        return repository.findAllByUsuario(pageable, authenticationFacade.getUser()).map(categoriaMapper::toDTO);
    }

    public CategoriaDTO create(CategoriaDTO form) {
        Categoria categoria = categoriaMapper.toEntity(form);
        categoria.setUsuario(authenticationFacade.getUser());
        return categoriaMapper.toDTO(repository.save(categoria));
    }

    public CategoriaDTO findById(Integer id) {
        Categoria categoria = repository.findByIdAndUsuario(id, authenticationFacade.getUser())
                .orElseThrow(() -> new RecursoInexistenteException(id, "categoria"));

        return categoriaMapper.toDTO(categoria);
    }

    public CategoriaDTO update(CategoriaDTO dto) {
        Categoria categoria = repository.findByIdAndUsuario(dto.getId(), authenticationFacade.getUser())
                .orElseThrow(() -> new RecursoInexistenteException(dto.getId(), "categoria"));

        BeanUtils.copyProperties(dto, categoria);
        categoria.setUsuario(authenticationFacade.getUser());

        if (dto.getPai() != null) {
            categoria.setPai(repository.findById(dto.getPai().getId()).orElse(null));
        } else {
            categoria.setPai(null);
        }

        return categoriaMapper.toDTO(repository.save(categoria));
    }

    public void delete(Integer id) {
        repository.findByIdAndUsuario(id, authenticationFacade.getUser())
                .orElseThrow(() -> new RecursoInexistenteException(id, "categoria"));

        repository.deleteById(id);
    }

}
