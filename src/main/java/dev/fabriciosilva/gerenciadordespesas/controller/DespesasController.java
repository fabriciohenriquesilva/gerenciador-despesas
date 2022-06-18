package dev.fabriciosilva.gerenciadordespesas.controller;

import dev.fabriciosilva.gerenciadordespesas.domain.Despesa;
import dev.fabriciosilva.gerenciadordespesas.dto.DespesaDto;
import dev.fabriciosilva.gerenciadordespesas.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping({"/", "/home", "/index"})
public class DespesasController {

    @Autowired
    private DespesaService despesaService;

    public String index(){
        return "index";
    }

    @GetMapping("/nova-despesa")
    public String getDespesaForm(DespesaDto despesaDto){
        return "despesa/formulario";
    }

    @PostMapping("/salvar-despesa")
    public String novo(@Valid DespesaDto despesaDto, BindingResult result){
        if(result.hasErrors()){
            return "despesa/formulario";
        }
        despesaService.novo(despesaDto);

        return "redirect:/";
    }
}
