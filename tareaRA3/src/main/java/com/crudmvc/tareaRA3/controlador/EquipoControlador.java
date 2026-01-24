package com.crudmvc.tareaRA3.controlador;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crudmvc.tareaRA3.entidad.Equipo;
import com.crudmvc.tareaRA3.servicio.EquipoServicio;

import jakarta.validation.Valid;

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

    @GetMapping("/nuevo")
    public String nuevoEquipo(Model model) {
        model.addAttribute("equipo", new Equipo());
        return "equipos/formulario";
    }

    @PostMapping
    public String crearEquipo(@Valid Equipo equipo, BindingResult result) {
        if (result.hasErrors()) {
            return "equipos/formulario";
        }
        equipoServicio.guardarEquipo(equipo);
        return "redirect:/equipos";
    }

    @GetMapping("/{id}/editar")
    public String editarEquipo(@PathVariable Long id, Model model) {
        Equipo equipo = equipoServicio.obtenerPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Id inválido: " + id));
        model.addAttribute("equipo", equipo);
        return "equipos/formulario";
    }

    @PostMapping("/{id}")
    public String actualizarEquipo(@PathVariable Long id, @Valid Equipo equipo, BindingResult result) {
        if (result.hasErrors()) {
            return "equipos/formulario";
        }
        equipo.setId(id);
        equipoServicio.guardarEquipo(equipo);
        return "redirect:/equipos";
    }

    @PostMapping("/{id}/eliminar")
    public String borrarEquipo(@PathVariable Long id) {
        equipoServicio.eliminarEquipo(id);
        return "redirect:/equipos";
    }
}

