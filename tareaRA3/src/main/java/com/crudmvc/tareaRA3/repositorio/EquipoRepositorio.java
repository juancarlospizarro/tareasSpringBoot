package com.crudmvc.tareaRA3.repositorio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crudmvc.tareaRA3.entidad.Equipo;

@Repository
public interface EquipoRepositorio extends JpaRepository<Equipo, Long> {
    Page<Equipo> findByNombre(String nombre, Pageable pageable);
    
    Page<Equipo> findAll(Pageable pageable);
}
