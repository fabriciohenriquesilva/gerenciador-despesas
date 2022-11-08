package dev.fabriciosilva.gerenciadordespesas.service;

import dev.fabriciosilva.gerenciadordespesas.domain.Categoria;
import dev.fabriciosilva.gerenciadordespesas.domain.Despesa;
import dev.fabriciosilva.gerenciadordespesas.dto.DespesaDto;
import dev.fabriciosilva.gerenciadordespesas.repository.CategoriaRepository;
import dev.fabriciosilva.gerenciadordespesas.repository.DespesaRepository;
import dev.fabriciosilva.gerenciadordespesas.request.DespesaPostRequestForm;
import dev.fabriciosilva.gerenciadordespesas.request.DespesaPutRequestForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Despesa novo(DespesaPostRequestForm despesaPostRequestForm){
        Despesa despesa = despesaPostRequestForm.toDespesa();

        Long categoriaId = Long.valueOf(despesaPostRequestForm.getCategoria());
        if(categoriaId != 0){
            Optional<Categoria> optionalCategoria = categoriaRepository.findById(categoriaId);
            if(optionalCategoria.isPresent()){
                Categoria categoria = optionalCategoria.get();
                despesa.setCategoria(categoria);
            }
        }

        despesaRepository.save(despesa);
        return despesa;
    }

    public Page<Despesa> listarTodos(Pageable pageable){
        return despesaRepository.findAll(pageable);
    }

    public void excluir(Integer id){
        despesaRepository.deleteById(id);
    }

    public DespesaDto detalhar(Integer id) {
        Optional<Despesa> optional = despesaRepository.findById(id);
        if(optional.isEmpty()){
            throw new RuntimeException("Not found");
        }
        return new DespesaDto(optional.get());
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

            Despesa despesa = despesaPutRequestForm.toDespesa();

            Long categoriaId = Long.valueOf(despesaPutRequestForm.getCategoria());
            if(categoriaId != 0){
                Optional<Categoria> optionalCategoria = categoriaRepository.findById(categoriaId);
                if(optionalCategoria.isPresent()){
                    Categoria categoria = optionalCategoria.get();
                    despesa.setCategoria(categoria);
                }
            }

            despesaRepository.save(despesa);
        }
    }
}
