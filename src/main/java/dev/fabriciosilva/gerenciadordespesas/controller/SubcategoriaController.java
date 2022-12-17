package dev.fabriciosilva.gerenciadordespesas.controller;

import dev.fabriciosilva.gerenciadordespesas.domain.Categoria;
import dev.fabriciosilva.gerenciadordespesas.domain.Subcategoria;
import dev.fabriciosilva.gerenciadordespesas.request.SubcategoriaPostRequestForm;
import dev.fabriciosilva.gerenciadordespesas.request.SubcategoriaPutRequestForm;
import dev.fabriciosilva.gerenciadordespesas.service.CategoriaService;
import dev.fabriciosilva.gerenciadordespesas.service.SubcategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/subcategoria")
public class SubcategoriaController {

    @Autowired
    private SubcategoriaService subcategoriaService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listarTodos(Model model) {
        List<Subcategoria> subcategorias = subcategoriaService.listarTodos();
        model.addAttribute("subcategorias", subcategorias);
        return "subcategoria/subcategoriaList";
    }

    @GetMapping("/form")
    public String getFormulario(Model model,
                                   SubcategoriaPostRequestForm subcategoriaPostRequestForm) {
        List<Categoria> categorias = categoriaService.listarTodos();
        model.addAttribute("categorias", categorias);

        return "subcategoria/subcategoriaForm";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid SubcategoriaPostRequestForm subcategoriaPostRequestForm,
                       BindingResult result) {
        if (result.hasErrors()) {
            return "subcategoria/subcategoriaForm";
        }
        subcategoriaService.save(subcategoriaPostRequestForm);
        return "redirect:/subcategoria";
    }

    @GetMapping("/detalhes/{id}")
    public String detalhar(@PathVariable Long id, Model pagina) {
        SubcategoriaPutRequestForm subcategoriaPutRequestForm = subcategoriaService.buscarPorId(id);
        pagina.addAttribute("subcategoriaPutRequestForm", subcategoriaPutRequestForm);

        List<Categoria> categorias = categoriaService.listarTodos();
        pagina.addAttribute("categorias", categorias);

        return "subcategoria/subcategoriaDetails";
    }

    @PostMapping("/editar")
    public String atualizar(@Valid SubcategoriaPutRequestForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "subcategoria/subcategoriaDetails";
        }
        subcategoriaService.edit(form);
        return "redirect:/subcategoria";
    }

    @PostMapping("/remover/{id}")
    public String remover(@PathVariable Long id) {
        subcategoriaService.excluir(id);
        return "redirect:/subcategoria";
    }
}
