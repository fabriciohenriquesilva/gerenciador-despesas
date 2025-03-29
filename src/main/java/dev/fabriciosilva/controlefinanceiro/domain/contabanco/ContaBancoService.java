package dev.fabriciosilva.controlefinanceiro.domain.contabanco;

import dev.fabriciosilva.controlefinanceiro.core.AbstractService;
import dev.fabriciosilva.controlefinanceiro.domain.contabanco.dto.ContaBancoCreateRequest;
import dev.fabriciosilva.controlefinanceiro.domain.contabanco.dto.ContaBancoResponse;
import dev.fabriciosilva.controlefinanceiro.domain.contabanco.dto.ContaBancoUpdateRequest;
import dev.fabriciosilva.controlefinanceiro.domain.user.User;
import dev.fabriciosilva.controlefinanceiro.infra.context.AuthenticationFacade;
import dev.fabriciosilva.controlefinanceiro.infra.exception.FormValidationException;
import dev.fabriciosilva.controlefinanceiro.infra.exception.RecursoInexistenteException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContaBancoService extends AbstractService<ContaBanco, Integer> {

    private final ContaBancoRespository contaBancoRespository;
    private final AuthenticationFacade authenticationFacade;
    private final ContaBancoMapper contaBancoMapper;


    public ContaBancoService(ContaBancoRespository contaBancoRespository, AuthenticationFacade authenticationFacade, ContaBancoMapper contaBancoMapper) {
        this.contaBancoRespository = contaBancoRespository;
        this.authenticationFacade = authenticationFacade;
        this.contaBancoMapper = contaBancoMapper;
    }

    @Override
    public ContaBancoRespository getRepository() {
        return this.contaBancoRespository;
    }

    public Page<ContaBancoResponse> findAll(Pageable paginacao) {
        User user = authenticationFacade.getUser();
        return contaBancoRespository.findAllByUsuario(paginacao, user).map(contaBancoMapper::toDTO);
    }

    public ContaBancoResponse create(ContaBancoCreateRequest form) {
        ContaBanco contaBanco = contaBancoMapper.toEntity(form);
        contaBanco.setUsuario(authenticationFacade.getUser());

        contaBanco = contaBancoRespository.save(contaBanco);

        return contaBancoMapper.toDTO(contaBanco);
    }

    public ContaBancoResponse findById(Integer id) {
        ContaBanco contaBanco = contaBancoRespository.findByIdAndUsuario(id, authenticationFacade.getUser())
                .orElseThrow(() -> new RecursoInexistenteException(id, "conta banco"));

        return contaBancoMapper.toDTO(contaBanco);
    }

    public ContaBancoResponse update(ContaBancoUpdateRequest form) {
        if (form.getId() == null) {
            throw new FormValidationException("Não foi informado o ID do registro a ser atualizado");
        }

        ContaBanco contaBanco = contaBancoRespository.findByIdAndUsuario(form.getId(), authenticationFacade.getUser())
                .orElseThrow(() -> new RecursoInexistenteException(form.getId(), "conta banco"));

        BeanUtils.copyProperties(form, contaBanco);

        contaBanco = contaBancoRespository.save(contaBanco);

        return contaBancoMapper.toDTO(contaBanco);
    }

    public void delete(Integer id) {
        contaBancoRespository.findByIdAndUsuario(id, authenticationFacade.getUser())
                .orElseThrow(() -> new RecursoInexistenteException(id, "conta banco"));

        contaBancoRespository.deleteById(id);
    }
}
