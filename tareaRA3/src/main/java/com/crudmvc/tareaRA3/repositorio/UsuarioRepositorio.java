package com.crudmvc.tareaRA3.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.crudmvc.tareaRA3.entidad.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
	  Page<Usuario> findByNombre(String username, Pageable pageable);
	  Usuario findByNombre(String nombre);
	  boolean existsByNombre(String nombre);
	}
