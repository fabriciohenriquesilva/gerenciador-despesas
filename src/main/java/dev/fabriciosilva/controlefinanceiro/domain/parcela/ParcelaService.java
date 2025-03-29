package dev.fabriciosilva.controlefinanceiro.domain.parcela;

import dev.fabriciosilva.controlefinanceiro.core.AbstractService;
import dev.fabriciosilva.controlefinanceiro.domain.contabanco.ContaBanco;
import dev.fabriciosilva.controlefinanceiro.domain.contabanco.ContaBancoService;
import dev.fabriciosilva.controlefinanceiro.domain.movimentofinanceiro.MovimentoFinanceiro;
import dev.fabriciosilva.controlefinanceiro.domain.parcela.dto.ParcelaPagamentoRequest;
import dev.fabriciosilva.controlefinanceiro.domain.parcela.dto.ParcelaResponse;
import dev.fabriciosilva.controlefinanceiro.domain.parcela.dto.ParcelaUpdateRequest;
import dev.fabriciosilva.controlefinanceiro.domain.user.User;
import dev.fabriciosilva.controlefinanceiro.infra.context.AuthenticationFacade;
import dev.fabriciosilva.controlefinanceiro.infra.exception.RecursoInexistenteException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional()
public class ParcelaService extends AbstractService<Parcela, Integer> {

    private final ParcelaRepository parcelaRepository;
    private final ParcelaMapper parcelaMapper;
    private final AuthenticationFacade authenticationFacade;
    private final ContaBancoService contaBancoService;

    public ParcelaService(ParcelaRepository parcelaRepository, ParcelaMapper parcelaMapper, AuthenticationFacade authenticationFacade, ContaBancoService contaBancoService) {
        this.parcelaRepository = parcelaRepository;
        this.parcelaMapper = parcelaMapper;
        this.authenticationFacade = authenticationFacade;
        this.contaBancoService = contaBancoService;
    }

    @Override
    public ParcelaRepository getRepository() {
        return parcelaRepository;
    }

    public List<ParcelaResponse> findAllByMovimentoFinanceiro(MovimentoFinanceiro movimentoFinanceiro) {
        return this.parcelaRepository.findAllByMovimentoFinanceiro(movimentoFinanceiro)
                .stream()
                .map(parcelaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void gerar(MovimentoFinanceiro movimentoFinanceiro, int quantidade) {
        BigDecimal valor = movimentoFinanceiro.getValor();
        BigDecimal valorParcela = valor.divide(BigDecimal.valueOf(quantidade), RoundingMode.HALF_DOWN);

        for (int i = 0; i < quantidade; i++) {
            Parcela parcela = new Parcela();

            parcela.setMovimentoFinanceiro(movimentoFinanceiro);
            parcela.setNumero(i + 1);
            parcela.setQuantidade(quantidade);
            parcela.setValorTotal(valorParcela);
            parcela.setDataVencimento(LocalDate.now().plusMonths(i + 1));

            this.save(parcela);
        }
    }

    public ParcelaResponse findById(Integer id) {
        User user = authenticationFacade.getUser();

        Parcela parcela = parcelaRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new RecursoInexistenteException(id, "parcela"));

        return parcelaMapper.toDTO(parcela);
    }

    public void deleteById(Integer id) {
        parcelaRepository.findByIdAndUser(id, authenticationFacade.getUser())
                .orElseThrow(() -> new RecursoInexistenteException(id, "parcela"));

        parcelaRepository.deleteById(id);
    }


    public ParcelaResponse update(ParcelaUpdateRequest form) {
        Integer id = form.getId();
        User user = authenticationFacade.getUser();

        Parcela parcela = parcelaRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new RecursoInexistenteException(id, "parcela"));

        BeanUtils.copyProperties(form, parcela);
        parcela = parcelaRepository.save(parcela);

        return parcelaMapper.toDTO(parcela);
    }

    public ParcelaResponse pagar(ParcelaPagamentoRequest form) {
        Integer id = form.getId();

        Parcela parcela = parcelaRepository.findByIdAndUser(id, authenticationFacade.getUser())
                .orElseThrow(() -> new RecursoInexistenteException(id, "parcela"));

        BeanUtils.copyProperties(form, parcela);

        if (!FormaPagamento.DINHEIRO.equals(form.getFormaPagamento())) {
            if (form.getContaBanco() != null) {
                ContaBanco contaBanco = contaBancoService.getById(form.getContaBanco());
                parcela.setContaBanco(contaBanco);
            }
        }

        parcela = parcelaRepository.save(parcela);

        return parcelaMapper.toDTO(parcela);
    }
}
