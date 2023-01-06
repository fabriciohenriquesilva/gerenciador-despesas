package dev.fabriciosilva.gerenciadordespesas.service;

import dev.fabriciosilva.gerenciadordespesas.domain.Categoria;
import dev.fabriciosilva.gerenciadordespesas.domain.Subcategoria;
import dev.fabriciosilva.gerenciadordespesas.repository.CategoriaRepository;
import dev.fabriciosilva.gerenciadordespesas.repository.SubcategoriaRepository;
import dev.fabriciosilva.gerenciadordespesas.dto.SubcategoriaDto;
import dev.fabriciosilva.gerenciadordespesas.request.SubcategoriaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubcategoriaService {

    @Autowired
    private SubcategoriaRepository subcategoriaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Subcategoria> listarTodos() {
        return subcategoriaRepository.findAll();
    }

    public Subcategoria save(SubcategoriaForm form) {
        Subcategoria subcategoria = new Subcategoria(form);

        Long categoriaId = Long.valueOf(form.getCategoria());
        if(categoriaId != 0){
            Optional<Categoria> optionalCategoria = categoriaRepository.findById(categoriaId);
            if(optionalCategoria.isPresent()){
                Categoria categoria = optionalCategoria.get();
                subcategoria.setCategoria(categoria);
            }
        }

        subcategoriaRepository.save(subcategoria);
        return subcategoria;
    }

    public SubcategoriaDto buscarPorId(Long id) {
        Optional<Subcategoria> optional = subcategoriaRepository.findById(id);
        Subcategoria subcategoria = optional.get();
        return subcategoria.toSubcategoriaDto();
    }

    public void edit(SubcategoriaDto form) {
        Long id = Long.valueOf(form.getId());
        Optional<Subcategoria> subcategoriaBuscada = subcategoriaRepository.findById(id);

        if (subcategoriaBuscada.isPresent()) {
            Subcategoria subcategoriaEditada = new Subcategoria(form);

            Long categoriaId = Long.valueOf(form.getCategoria());
            Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);
            if(categoria.isPresent()){
                subcategoriaEditada.setCategoria(categoria.get());
            }
            subcategoriaRepository.save(subcategoriaEditada);
        }
    }

    public void excluir(Long id) {
        subcategoriaRepository.deleteById(id);
    }
}
