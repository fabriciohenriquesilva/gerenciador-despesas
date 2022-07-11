package dev.fabriciosilva.gerenciadordespesas.controller;

import dev.fabriciosilva.gerenciadordespesas.domain.Despesa;
import dev.fabriciosilva.gerenciadordespesas.request.DespesaPostRequestForm;
import dev.fabriciosilva.gerenciadordespesas.request.DespesaPutRequestForm;
import dev.fabriciosilva.gerenciadordespesas.service.DespesaService;
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
@RequestMapping("/")
public class DespesasController {

    @Autowired
    private DespesaService despesaService;

    @GetMapping
    public String index(Model model){
        List<Despesa> despesas = despesaService.listarTodos();
        model.addAttribute("despesas", despesas);
        return "index";
    }

    @GetMapping("/nova-despesa")
    public String getDespesaForm(DespesaPutRequestForm despesaPutRequestForm){
        return "despesa/formulario";
    }

    @PostMapping("/salvar-despesa")
    public String novo(@Valid DespesaPostRequestForm despesaPostRequestForm, BindingResult result){
        if(result.hasErrors()){
            return "despesa/formulario";
        }
        despesaService.novo(despesaPostRequestForm);

        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String excluir(@PathVariable Integer id){
        despesaService.excluir(id);
        return "redirect:/";
    }

    @GetMapping("/visualizar/{id}")
    public String visualizar(@PathVariable Integer id, Model model){
        DespesaPutRequestForm despesaPutRequestForm = despesaService.buscarPorId(id);
        model.addAttribute("despesaPutRequestForm", despesaPutRequestForm);
        return "despesa/despesaDetails";
    }

    @PostMapping("/editar-despesa")
    public String editar(@Valid DespesaPutRequestForm despesaPutRequestForm, BindingResult result){
        if(result.hasErrors()){
            return "despesa/formulario";
        }
        despesaService.editar(despesaPutRequestForm);

        return "redirect:/";
    }

}
