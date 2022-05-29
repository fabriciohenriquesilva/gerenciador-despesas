package dev.fabriciosilva.gerenciadordespesas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DespesasController {

    @RequestMapping({"/", "/home", "/index"})
    public String index(){
        return "index";
    }
}
