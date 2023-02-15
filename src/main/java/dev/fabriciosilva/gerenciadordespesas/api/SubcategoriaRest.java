package dev.fabriciosilva.gerenciadordespesas.api;

import dev.fabriciosilva.gerenciadordespesas.dto.SubcategoriaDto;
import dev.fabriciosilva.gerenciadordespesas.repository.SubcategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/subcategorias")
public class SubcategoriaRest {

    @Autowired
    private SubcategoriaRepository repository;

    @GetMapping
    public List<SubcategoriaDto> listar() {
        return repository.findAll().stream()
                .map(SubcategoriaDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping(params = "categoria")
    public List<SubcategoriaDto> listarPorCategoria(@RequestParam Long categoria) {
        return repository.findByCategoria(categoria).stream()
                .map(SubcategoriaDto::new)
                .collect(Collectors.toList());
    }

}
