package com.crudmvc.tareaRA3.controlador;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.crudmvc.tareaRA3.entidad.Usuario;
import com.crudmvc.tareaRA3.repositorio.UsuarioRepositorio;

@ControllerAdvice
public class UsuarioControllerAdvice {

    private final UsuarioRepositorio usuarioRepositorio;

    public UsuarioControllerAdvice(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @ModelAttribute("usuarioConectado")
    public Usuario usuarioLogueado() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
            return null;
        }

        String username = auth.getName();
        return usuarioRepositorio.findByNombre(username);
    }
}