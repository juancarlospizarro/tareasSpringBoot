package com.crudmvc.tarea3.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crudmvc.tarea3.entidad.Equipo;

@Repository
public interface EquipoRepositorio extends JpaRepository<Equipo, Long> {
    List<Equipo> findByNombre(String nombre);
}
