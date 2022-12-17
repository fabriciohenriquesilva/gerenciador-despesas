package dev.fabriciosilva.gerenciadordespesas.controller;

import dev.fabriciosilva.gerenciadordespesas.domain.Categoria;
import dev.fabriciosilva.gerenciadordespesas.request.CategoriaPostRequestForm;
import dev.fabriciosilva.gerenciadordespesas.request.CategoriaPutRequestForm;
import dev.fabriciosilva.gerenciadordespesas.service.CategoriaService;
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
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listarTodos(Model model){
        List<Categoria> categorias = categoriaService.listarTodos();
        model.addAttribute("categorias", categorias);
        return "categoria/categoriaList";
    }

    @GetMapping("/form")
    public String getFormulario(CategoriaPostRequestForm categoriaPostRequestForm){
        return "categoria/categoriaForm";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid CategoriaPostRequestForm categoriaPostRequestForm,
                       BindingResult result){
        if(result.hasErrors()){
            return "categoria/categoriaForm";
        }
        categoriaService.save(categoriaPostRequestForm);
        return "redirect:/categoria";
    }

    @GetMapping("/detalhes/{id}")
    public String detalhar(@PathVariable Long id, Model pagina){
        CategoriaPutRequestForm categoriaPutRequestForm = categoriaService.buscarPorId(id);
        pagina.addAttribute("categoriaPutRequestForm", categoriaPutRequestForm);
        return "categoria/categoriaDetails";
    }

    @PostMapping("/editar")
    public String atualizar(@Valid CategoriaPutRequestForm form, BindingResult result){
        if(result.hasErrors()){
            return "categoria/categoriaDetails";
        }
        categoriaService.edit(form);
        return "redirect:/categoria";
    }

    @PostMapping("/remover/{id}")
    public String remover(@PathVariable Long id){
        categoriaService.excluir(id);
        return "redirect:/categoria";
    }

}
