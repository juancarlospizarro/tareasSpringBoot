package com.crudmvc.tareaRA3.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudmvc.tareaRA3.entidad.Equipo;
import com.crudmvc.tareaRA3.repositorio.EquipoRepositorio;

@Service
public class EquipoServicio {

    @Autowired
    private EquipoRepositorio equipoRepositorio;


    public Equipo guardarEquipo(Equipo equipo) {
        return equipoRepositorio.save(equipo);
    }

	public List<Equipo> obtenerTodos() {
		return equipoRepositorio.findAll();
	}
	
	public void eliminarEquipo(Long id) {
	    equipoRepositorio.deleteById(id);
	}
	
	public Optional<Equipo> obtenerPorId(Long id) {
		return equipoRepositorio.findById(id);
	}
	
	public List<Equipo> buscarPorNombre(String nombre) {
	    return equipoRepositorio.findByNombre(nombre);
	}


}
