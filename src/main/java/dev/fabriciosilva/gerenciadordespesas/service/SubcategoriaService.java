package dev.fabriciosilva.gerenciadordespesas.service;

import dev.fabriciosilva.gerenciadordespesas.domain.Categoria;
import dev.fabriciosilva.gerenciadordespesas.domain.Subcategoria;
import dev.fabriciosilva.gerenciadordespesas.repository.CategoriaRepository;
import dev.fabriciosilva.gerenciadordespesas.repository.SubcategoriaRepository;
import dev.fabriciosilva.gerenciadordespesas.request.SubcategoriaPostRequestForm;
import dev.fabriciosilva.gerenciadordespesas.request.SubcategoriaPutRequestForm;
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

    public Subcategoria save(SubcategoriaPostRequestForm subcategoriaPostRequestForm) {
        Subcategoria subcategoria = subcategoriaPostRequestForm.toSubcategoria();

        Long categoriaId = Long.valueOf(subcategoriaPostRequestForm.getCategoria());
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

    public SubcategoriaPutRequestForm buscarPorId(Long id) {
        Optional<Subcategoria> optional = subcategoriaRepository.findById(id);
        Subcategoria subcategoria = optional.get();
        return subcategoria.toSubcategoriaDto();
    }

    public void edit(SubcategoriaPutRequestForm form) {
        Long id = Long.valueOf(form.getId());
        Optional<Subcategoria> subcategoriaBuscada = subcategoriaRepository.findById(id);

        if (subcategoriaBuscada.isPresent()) {
            Subcategoria subcategoriaEditada = form.toSubcategoria();

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
