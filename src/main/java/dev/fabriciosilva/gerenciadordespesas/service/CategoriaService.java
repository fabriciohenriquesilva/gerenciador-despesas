package dev.fabriciosilva.gerenciadordespesas.service;

import dev.fabriciosilva.gerenciadordespesas.domain.Categoria;
import dev.fabriciosilva.gerenciadordespesas.repository.CategoriaRepository;
import dev.fabriciosilva.gerenciadordespesas.request.CategoriaForm;
import dev.fabriciosilva.gerenciadordespesas.dto.CategoriaDto;
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

    public Categoria save(CategoriaForm form) {
        Categoria categoria = new Categoria(form);
        categoriaRepository.save(categoria);
        return categoria;
    }

    public CategoriaDto buscarPorId(Long id) {
        Optional<Categoria> optional = categoriaRepository.findById(id);
        Categoria categoria = optional.get();
        return new CategoriaDto(categoria);
    }

    public void edit(CategoriaDto dto){
        String categoriaId = dto.getId();
        Long id = Long.valueOf(categoriaId);

        Optional<Categoria> optional = categoriaRepository.findById(id);
        if(optional.isPresent()){
            if(optional.get().getId() == id){
                categoriaRepository.save(new Categoria(dto));
            }
        }
    }

    public void excluir(Long id){
        categoriaRepository.deleteById(id);
    }
}
