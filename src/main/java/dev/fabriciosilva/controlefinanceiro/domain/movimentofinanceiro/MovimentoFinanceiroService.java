package dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro;

import dev.fabriciosilva.controlefinanceiro.core.AbstractService;
import dev.fabriciosilva.controlefinanceiro.domain.categoria.Categoria;
import dev.fabriciosilva.controlefinanceiro.domain.categoria.CategoriaService;
import dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro.dto.MovimentoFinanceiroCreateRequest;
import dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro.dto.MovimentoFinanceiroResponse;
import dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro.dto.MovimentoFinanceiroUpdateRequest;
import dev.fabriciosilva.controlefinanceiro.domain.parcela.ParcelaService;
import dev.fabriciosilva.controlefinanceiro.domain.parcela.dto.ParcelaResponse;
import dev.fabriciosilva.controlefinanceiro.domain.user.User;
import dev.fabriciosilva.controlefinanceiro.infra.context.AuthenticationFacade;
import dev.fabriciosilva.controlefinanceiro.infra.exception.FormValidationException;
import dev.fabriciosilva.controlefinanceiro.infra.exception.RecursoInexistenteException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional()
public class MovimentoFinanceiroService extends AbstractService<MovimentoFinanceiro, Integer> {

    private final MovimentoFinanceiroRepository repository;
    private final ParcelaService parcelaService;
    private final MovimentoFinanceiroMapper mapper;
    private final CategoriaService categoriaService;
    private final AuthenticationFacade authenticationFacade;

    public MovimentoFinanceiroService(MovimentoFinanceiroRepository repository, ParcelaService parcelaService, MovimentoFinanceiroMapper mapper, CategoriaService categoriaService, AuthenticationFacade authenticationFacade) {
        this.repository = repository;
        this.parcelaService = parcelaService;
        this.mapper = mapper;
        this.categoriaService = categoriaService;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public MovimentoFinanceiroRepository getRepository() {
        return repository;
    }

    public Page<MovimentoFinanceiroResponse> findAll(Pageable pageable) {
        User user = authenticationFacade.getUser();
        return repository.findAllByUsuario(pageable, user).map(mapper::toDTO);
    }

    public MovimentoFinanceiroResponse create(MovimentoFinanceiroCreateRequest form) {
        MovimentoFinanceiro movimentoFinanceiro = mapper.toEntity(form);

        User user = authenticationFacade.getUser();
        movimentoFinanceiro.setUsuario(user);

        movimentoFinanceiro = repository.save(movimentoFinanceiro);

        this.parcelaService.gerar(movimentoFinanceiro, form.getQuantidadeParcelas());

        return mapper.toDTO(movimentoFinanceiro);
    }

    public MovimentoFinanceiroResponse findById(Integer id) {
        User user = authenticationFacade.getUser();

        MovimentoFinanceiro movimentoFinanceiro = repository.findByIdAndUsuario(id, user)
                .orElseThrow(() -> new RecursoInexistenteException(id, "movimento financeiro"));

        return mapper.toDTO(movimentoFinanceiro);
    }

    public MovimentoFinanceiroResponse update(MovimentoFinanceiroUpdateRequest form) {
        if (form.getId() == null) {
            throw new FormValidationException("Não foi informado o ID do registro a ser atualizado");
        }

        MovimentoFinanceiro movimentoFinanceiro = repository.findById(form.getId())
                .orElseThrow(() -> new RecursoInexistenteException(form.getId(), "movimento financeiro"));

        BeanUtils.copyProperties(form, movimentoFinanceiro);

        Categoria categoria = categoriaService.getById(form.getCategoria().getId());
        movimentoFinanceiro.setCategoria(categoria);

        MovimentoFinanceiro saved = repository.save(movimentoFinanceiro);

        return mapper.toDTO(saved);
    }

    public void delete(Integer id) {
        boolean existe = repository.existsById(id);
        if (!existe) {
            throw new RecursoInexistenteException(id, "movimento financeiro");
        }
        repository.deleteById(id);
    }

    public List<ParcelaResponse> getParcelas(Integer id) {
        User user = authenticationFacade.getUser();
        MovimentoFinanceiro movimentoFinanceiro = repository.findByIdAndUsuario(id, user)
                .orElseThrow(() -> new RecursoInexistenteException(id, "movimento financeiro"));

        return this.parcelaService.findAllByMovimentoFinanceiro(movimentoFinanceiro);
    }
}
