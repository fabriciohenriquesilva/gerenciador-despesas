package dev.fabriciosilva.gerenciadordespesas.controller;

import dev.fabriciosilva.gerenciadordespesas.domain.Despesa;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DespesasController {

    @RequestMapping({"/", "/home", "/index"})
    public String index(){
        return "index";
    }

    @GetMapping("/nova-despesa")
    public String getDespesaForm(){
        return "despesa-form";
    }

    @PostMapping("/nova-despesa")
    public String save(Despesa despesa){

        return "success";
    }
}
