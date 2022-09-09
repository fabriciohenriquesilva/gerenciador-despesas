package dev.fabriciosilva.gerenciadordespesas.service;

import dev.fabriciosilva.gerenciadordespesas.domain.Categoria;
import dev.fabriciosilva.gerenciadordespesas.repository.CategoriaRepository;
import dev.fabriciosilva.gerenciadordespesas.request.CategoriaPostRequestForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarTodos(){
        return categoriaRepository.findAll();
    }

    public Categoria save(CategoriaPostRequestForm categoriaPostRequestForm){
        Categoria categoria = categoriaPostRequestForm.toCategoria();
        categoriaRepository.save(categoria);
        return categoria;
    }
}
