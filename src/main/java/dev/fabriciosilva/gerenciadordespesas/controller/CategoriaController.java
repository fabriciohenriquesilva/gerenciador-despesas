package dev.fabriciosilva.gerenciadordespesas.controller;

import dev.fabriciosilva.gerenciadordespesas.domain.Categoria;
import dev.fabriciosilva.gerenciadordespesas.request.CategoriaPostRequestForm;
import dev.fabriciosilva.gerenciadordespesas.request.CategoriaPutRequestForm;
import dev.fabriciosilva.gerenciadordespesas.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listaCategorias(Model model){
        List<Categoria> categorias = categoriaService.listarTodos();
        model.addAttribute("categorias", categorias);
        return "categoria/listaCategorias";
    }

    @GetMapping("/nova-categoria")
    public String getCategoriaForm(CategoriaPostRequestForm categoriaPostRequestForm){
        return "categoria/categoriaForm";
    }

    @PostMapping("/salvar")
    public String save(CategoriaPostRequestForm categoriaPostRequestForm){
        categoriaService.save(categoriaPostRequestForm);

        return "redirect:/categoria";
    }

    @GetMapping("/detalhes/{id}")
    public String visualize(@PathVariable Long id, Model pagina){
        CategoriaPutRequestForm categoriaPutRequestForm = categoriaService.buscarPorId(id);
        pagina.addAttribute("categoriaPutRequestForm", categoriaPutRequestForm);
        return "categoria/categoriaDetalhes";
    }

}
