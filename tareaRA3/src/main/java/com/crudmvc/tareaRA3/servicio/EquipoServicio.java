package com.crudmvc.tareaRA3.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudmvc.tareaRA3.entidad.Equipo;
import com.crudmvc.tareaRA3.repositorio.EquipoRepositorio;

public interface EquipoServicio {
    List<Equipo> obtenerTodos();
    Equipo guardarEquipo(Equipo equipo);
    Optional<Equipo> obtenerPorId(Long id);
    void eliminarEquipo(Long id);
    List<Equipo> buscarPorNombre(String nombre);
}