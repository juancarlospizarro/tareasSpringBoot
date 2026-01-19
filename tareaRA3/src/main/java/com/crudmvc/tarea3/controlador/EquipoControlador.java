package com.crudmvc.tarea3.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crudmvc.tarea3.entidad.Equipo;
import com.crudmvc.tarea3.servicio.EquipoServicio;

@Controller
@RequestMapping("/equipos")
public class EquipoControlador {

	@Autowired
	private EquipoServicio equipoServicio;

	@GetMapping
	public String listarEquipos(Model model) {
		model.addAttribute("equipos", equipoServicio.obtenerTodos());
		return "equipos/lista";
	}

	@GetMapping("/equipos/nuevo")
	public String mostrarFormularioDeNuevoEquipo(Model model) {
		model.addAttribute("equipo", new Equipo());
		return "equipos/formulario";
	}

	@PostMapping("/equipos/nuevo")
	public String guardarEquipo(Equipo equipo, BindingResult result) {
		if (result.hasErrors()) {
			return "equipos/formulario";
		}
		equipoServicio.guardarEquipo(equipo);
		return "redirect:/equipos";
	}

}
