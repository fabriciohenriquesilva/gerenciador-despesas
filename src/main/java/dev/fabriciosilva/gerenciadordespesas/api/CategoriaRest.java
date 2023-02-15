package dev.fabriciosilva.gerenciadordespesas.api;

import dev.fabriciosilva.gerenciadordespesas.dto.CategoriaDto;
import dev.fabriciosilva.gerenciadordespesas.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/categorias")
public class CategoriaRest {

    @Autowired
    private CategoriaRepository repository;

    @GetMapping
    public List<CategoriaDto> listar() {
        return repository.findAll().stream()
                .map(CategoriaDto::new)
                .collect(Collectors.toList());
    }
}
