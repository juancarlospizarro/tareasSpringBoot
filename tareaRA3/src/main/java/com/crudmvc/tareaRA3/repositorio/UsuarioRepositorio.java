package com.crudmvc.tareaRA3.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crudmvc.tareaRA3.entidad.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
	  Usuario findByNombre(String username);
	}
