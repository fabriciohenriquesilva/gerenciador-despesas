package dev.fabriciosilva.controlefinanceiro.domain.categoria;

import dev.fabriciosilva.controlefinanceiro.infra.exception.RecursoInexistenteException;
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

    public Categoria save(CategoriaDto form) {
        return categoriaRepository.save(form.toCategoria());
    }

    public CategoriaDto buscarPorId(Long id) {
        Optional<Categoria> optional = categoriaRepository.findById(id);
        Categoria categoria = optional.get();
        return new CategoriaDto(categoria);
    }

    public void edit(CategoriaDto dto){
        Long id = dto.getId();

        Optional<Categoria> optional = categoriaRepository.findById(id);
        if(optional.isPresent()){
            if(optional.get().getId() == id){
                categoriaRepository.save(dto.toCategoria());
            }
        }
    }

    public void excluir(Long id){
        boolean exists = categoriaRepository.existsById(id);
        if(!exists) {
            throw new RecursoInexistenteException("Elemento de id " + id + " não foi encontrado para exclusão");
        }
        categoriaRepository.deleteById(id);
    }
}
