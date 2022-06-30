package dev.fabriciosilva.gerenciadordespesas.service;

import dev.fabriciosilva.gerenciadordespesas.domain.Despesa;
import dev.fabriciosilva.gerenciadordespesas.dto.DespesaDto;
import dev.fabriciosilva.gerenciadordespesas.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    public Despesa novo(DespesaDto despesaDto){
        Despesa despesa = despesaDto.toDespesa();
        despesaRepository.save(despesa);
        return despesa;
    }

    public List<Despesa> listarTodos(){
        return despesaRepository.findAll();
    }

    public void excluir(Integer id){
        despesaRepository.deleteById(id);
    }

    public DespesaDto buscarPorId(Integer id){
        Despesa despesa = despesaRepository.findById(id).get();
        DespesaDto despesaDto = despesa.toDespesaDto();
        return despesaDto;
    }
}
