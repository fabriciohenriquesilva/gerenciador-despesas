package dev.fabriciosilva.gerenciadordespesas.controller;

import dev.fabriciosilva.gerenciadordespesas.domain.Categoria;
import dev.fabriciosilva.gerenciadordespesas.domain.Despesa;
import dev.fabriciosilva.gerenciadordespesas.domain.Subcategoria;
import dev.fabriciosilva.gerenciadordespesas.dto.PessoaDto;
import dev.fabriciosilva.gerenciadordespesas.request.DespesaPostRequestForm;
import dev.fabriciosilva.gerenciadordespesas.request.DespesaPutRequestForm;
import dev.fabriciosilva.gerenciadordespesas.service.CategoriaService;
import dev.fabriciosilva.gerenciadordespesas.service.DespesaService;
import dev.fabriciosilva.gerenciadordespesas.service.PessoaService;
import dev.fabriciosilva.gerenciadordespesas.service.SubcategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/despesa")
public class DespesasController {

    @Autowired
    private DespesaService despesaService;
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private SubcategoriaService subcategoriaService;

    @Autowired
    private PessoaService credorService;

    @GetMapping
    public String listarTodos(Pageable pageable, Model model){
        int page = pageable.getPageNumber();
        PageRequest paginacao = PageRequest.of(page, 10);

        Page<Despesa> despesas = despesaService.listarTodos(paginacao);
        model.addAttribute("despesas", despesas);
        model.addAttribute("paginacao", paginacao);

        return "index";
    }

    @GetMapping("/form")
    public String getFormulario(Model model,
                                 DespesaPostRequestForm despesaPostRequestForm){

        List<Categoria> categorias = categoriaService.listarTodos();
        model.addAttribute("categorias", categorias);

        List<Subcategoria> subcategorias = subcategoriaService.listarTodos();
        model.addAttribute("subcategorias", subcategorias);

        return "despesa/despesaForm";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid DespesaPostRequestForm despesaPostRequestForm, BindingResult result){
        if(result.hasErrors()){
            return "despesa/despesaForm";
        }
        despesaService.novo(despesaPostRequestForm);

        return "redirect:/despesa";
    }

    @PostMapping("/remover/{id}")
    public String remover(@PathVariable Integer id){
        despesaService.excluir(id);
        return "redirect:/despesa";
    }

    @GetMapping("/detalhes/{id}")
    public String detalhar(@PathVariable Integer id, Model model){
        DespesaPutRequestForm despesaPutRequestForm = despesaService.buscarPorId(id);
        model.addAttribute("despesaPutRequestForm", despesaPutRequestForm);

        List<Categoria> categorias = categoriaService.listarTodos();
        model.addAttribute("categorias", categorias);

        List<Subcategoria> subcategorias = subcategoriaService.listarTodos();
        model.addAttribute("subcategorias", subcategorias);

        PessoaDto pessoaDto = credorService.buscarPorId(Long.valueOf(despesaPutRequestForm.getCredor()));
        model.addAttribute("credor", pessoaDto);

        return "despesa/despesaDetails";
    }

    @PostMapping("/editar")
    public String atualizar(@Valid DespesaPutRequestForm despesaPutRequestForm, BindingResult result){
        if(result.hasErrors()){
            return "despesa/despesaDetails";
        }
        despesaService.editar(despesaPutRequestForm);

        return "redirect:/despesa";
    }

}
