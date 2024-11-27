package dev.fabriciosilva.controlefinanceiro.domain.parcela;

import dev.fabriciosilva.controlefinanceiro.core.ServiceContract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional()
public class ParcelaService implements ServiceContract<ParcelaDTO, Integer> {

    private final ParcelaRepository parcelaRepository;
    private final ParcelaMapper parcelaMapper;

    public ParcelaService(ParcelaRepository parcelaRepository, ParcelaMapper parcelaMapper) {
        this.parcelaRepository = parcelaRepository;
        this.parcelaMapper = parcelaMapper;
    }

    @Override
    public Page<ParcelaDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public ParcelaDTO save(ParcelaDTO form) {
        Parcela parcela = parcelaMapper.toEntity(form);
        parcela.setProcessamento(LocalDate.now());

        parcela = parcelaRepository.save(parcela);

        return parcelaMapper.toDTO(parcela);
    }

    public Parcela save(Parcela form) {
        return this.parcelaRepository.save(form);
    }

    @Override
    public ParcelaDTO findById(Integer integer) {
        return null;
    }

    @Override
    public ParcelaDTO update(ParcelaDTO form) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }
}
