package dev.fabriciosilva.gerenciadordespesas.service;

import dev.fabriciosilva.gerenciadordespesas.domain.Subcategoria;
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

    public List<Subcategoria> listarTodos() {
        return subcategoriaRepository.findAll();
    }

    public Subcategoria save(SubcategoriaPostRequestForm subcategoriaPostRequestForm) {
        Subcategoria subcategoria = subcategoriaPostRequestForm.toSubcategoria();
        subcategoriaRepository.save(subcategoria);
        return subcategoria;
    }

    public SubcategoriaPutRequestForm buscarPorId(Long id) {
        Optional<Subcategoria> optional = subcategoriaRepository.findById(id);
        Subcategoria subcategoria = optional.get();
        return subcategoria.toSubcategoriaDto();
    }

    public void edit(SubcategoriaPutRequestForm form) {
        String categoriaId = form.getId();
        Long id = Long.valueOf(categoriaId);
        Optional<Subcategoria> categoriaBuscada = subcategoriaRepository.findById(id);
        if (categoriaBuscada.isPresent()) {
            Subcategoria categoriaEditada = form.toSubcategoria();
            subcategoriaRepository.save(categoriaEditada);
        }
    }

    public void excluir(Long id) {
        subcategoriaRepository.deleteById(id);
    }
}
