package com.crudmvc.tareaRA3.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudmvc.tareaRA3.entidad.Equipo;
import com.crudmvc.tareaRA3.repositorio.EquipoRepositorio;

@Service
public class EquipoServicioImpl implements EquipoServicio {

    @Autowired
    private EquipoRepositorio equipoRepositorio;

    @Override
    public Equipo guardarEquipo(Equipo equipo) {
        return equipoRepositorio.save(equipo);
    }

    @Override
	public List<Equipo> obtenerTodos() {
		return equipoRepositorio.findAll();
	}
	
    @Override
	public void eliminarEquipo(Long id) {
	    equipoRepositorio.deleteById(id);
	}
	
    @Override
	public Optional<Equipo> obtenerPorId(Long id) {
		return equipoRepositorio.findById(id);
	}
	
    @Override
	public List<Equipo> buscarPorNombre(String nombre) {
	    return equipoRepositorio.findByNombre(nombre);
	}
}
