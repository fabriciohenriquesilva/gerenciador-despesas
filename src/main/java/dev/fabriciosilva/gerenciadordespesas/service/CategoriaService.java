package dev.fabriciosilva.gerenciadordespesas.service;

import dev.fabriciosilva.gerenciadordespesas.domain.Categoria;
import dev.fabriciosilva.gerenciadordespesas.repository.CategoriaRepository;
import dev.fabriciosilva.gerenciadordespesas.request.CategoriaPostRequestForm;
import dev.fabriciosilva.gerenciadordespesas.request.CategoriaPutRequestForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarTodos() {
        return categoriaRepository.findAll();
    }

    public Categoria save(CategoriaPostRequestForm categoriaPostRequestForm) {
        Categoria categoria = categoriaPostRequestForm.toCategoria();
        categoriaRepository.save(categoria);
        return categoria;
    }

    public CategoriaPutRequestForm buscarPorId(Long id) {
        Optional<Categoria> optional = categoriaRepository.findById(id);
        Categoria categoria = optional.get();
        return categoria.toCategoriaDto();
    }

    public void edit(CategoriaPutRequestForm form){
        String categoriaId = form.getId();
        Long id = Long.valueOf(categoriaId);
        Optional<Categoria> categoriaBuscada = categoriaRepository.findById(id);
        if(categoriaBuscada.isPresent()){
            Categoria categoriaEditada = form.toCategoria();
            categoriaRepository.save(categoriaEditada);
        }
    }
}
