package dev.fabriciosilva.gerenciadordespesas.service;

import dev.fabriciosilva.gerenciadordespesas.domain.Despesa;
import dev.fabriciosilva.gerenciadordespesas.request.DespesaPostRequestForm;
import dev.fabriciosilva.gerenciadordespesas.request.DespesaPutRequestForm;
import dev.fabriciosilva.gerenciadordespesas.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    public Despesa novo(DespesaPostRequestForm despesaPostRequestForm){
        Despesa despesa = despesaPostRequestForm.toDespesa();
        despesaRepository.save(despesa);
        return despesa;
    }

    public List<Despesa> listarTodos(){
        return despesaRepository.findAll();
    }

    public void excluir(Integer id){
        despesaRepository.deleteById(id);
    }

    public DespesaPutRequestForm buscarPorId(Integer id){
        Despesa despesa = despesaRepository.findById(id).get();
        DespesaPutRequestForm despesaPutRequestForm = despesa.toDespesaDto();
        return despesaPutRequestForm;
    }

    public void editar(DespesaPutRequestForm despesaPutRequestForm){
        String despesaId = despesaPutRequestForm.getId();
        Optional<Despesa> optional = despesaRepository.findById(Integer.valueOf(despesaId));

        boolean despesaExiste = optional.isPresent();

        if(despesaExiste){
            despesaRepository.save(despesaPutRequestForm.toDespesa());
        }
    }
}
