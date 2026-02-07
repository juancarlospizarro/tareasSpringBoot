package com.crudmvc.tareaRA3.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioControlador {

	@GetMapping("/inicio")
    public String inicio() {
        return "inicio";
    }
	
	@GetMapping("/")
    public String index() {
        return "index";
    }
	
}
