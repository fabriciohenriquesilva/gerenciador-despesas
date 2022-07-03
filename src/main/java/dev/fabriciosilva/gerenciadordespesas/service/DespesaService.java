package dev.fabriciosilva.gerenciadordespesas.service;

import dev.fabriciosilva.gerenciadordespesas.domain.Despesa;
import dev.fabriciosilva.gerenciadordespesas.request.DespesaRequestForm;
import dev.fabriciosilva.gerenciadordespesas.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    public Despesa novo(DespesaRequestForm despesaRequestForm){
        Despesa despesa = despesaRequestForm.toDespesa();
        despesaRepository.save(despesa);
        return despesa;
    }

    public List<Despesa> listarTodos(){
        return despesaRepository.findAll();
    }

    public void excluir(Integer id){
        despesaRepository.deleteById(id);
    }

    public DespesaRequestForm buscarPorId(Integer id){
        Despesa despesa = despesaRepository.findById(id).get();
        DespesaRequestForm despesaRequestForm = despesa.toDespesaDto();
        return despesaRequestForm;
    }

    public void editar(DespesaRequestForm despesaRequestForm){
        String despesaId = despesaRequestForm.getId();
        Optional<Despesa> optional = despesaRepository.findById(Integer.valueOf(despesaId));

        boolean despesaExiste = optional.isPresent();

        if(despesaExiste){
            despesaRepository.save(despesaRequestForm.toDespesa());
        }
    }
}
