package com.crudmvc.tareaRA3.servicio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crudmvc.tareaRA3.entidad.Rol;
import com.crudmvc.tareaRA3.entidad.Usuario;
import com.crudmvc.tareaRA3.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

  private final UsuarioRepositorio usuarioRepositorio;
  private final PasswordEncoder passwordEncoder;

  public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio, PasswordEncoder passwordEncoder) {
    this.usuarioRepositorio = usuarioRepositorio;
    this.passwordEncoder = passwordEncoder;
  }
  
  @Override
  public Usuario crear(String nombre, String contrasenaEnClaro, Rol rol) {
    if (usuarioRepositorio.findByNombre(nombre) != null) {
      throw new IllegalArgumentException("Ya existe un usuario con ese nombre");
    }
    Usuario u = new Usuario();
    u.setNombre(nombre);
    u.setContrasena(contrasenaEnClaro);
    u.setRol(rol);
    return usuarioRepositorio.save(u);
  }

  @Override
  public Page<Usuario> buscarPorNombre(String nombre, Pageable pageable) {
    return usuarioRepositorio.findByNombre(nombre, pageable);
  }

  @Override
  public Usuario obtenerPorId(Long id) {
    return usuarioRepositorio.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id=" + id));
  }

  @Override
  public List<Usuario> listar() {
    return usuarioRepositorio.findAll();
  }

  @Override
  public Page<Usuario> listar(Pageable pageable) {
    return usuarioRepositorio.findAll(pageable);
  }

  @Override
  public Usuario actualizar(Long id, String nuevoNombre, Rol nuevoRol, String nuevaContrasena) {
    Usuario u = obtenerPorId(id);
    u.setNombre(nuevoNombre);
    u.setRol(nuevoRol);
    u.setContrasena(nuevaContrasena);
    return usuarioRepositorio.save(u);
  }

  @Override
  public void cambiarContrasena(Long id, String contrasenaActualEnClaro, String nuevaContrasenaEnClaro) {
    Usuario u = obtenerPorId(id);
    if (!passwordEncoder.matches(contrasenaActualEnClaro, u.getContrasena())) {
      throw new IllegalArgumentException("La contraseña actual no es correcta");
    }
    u.setContrasena(passwordEncoder.encode(nuevaContrasenaEnClaro));
    usuarioRepositorio.save(u);
  }

  @Override
  public void eliminar(Long id) {
    usuarioRepositorio.deleteById(id);
  }

  @Override
  public Usuario obtenerUsuarioConectado() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null
        && !(authentication instanceof AnonymousAuthenticationToken)
        && authentication.isAuthenticated()) {

      String nombreUsuarioConectado = authentication.getName();
      return usuarioRepositorio.findByNombre(nombreUsuarioConectado);
    }
    return null;
  }

  @Override
  public Usuario obtenerPorNombre(String nombre) {
	// TODO Auto-generated method stub
	return null;
  }
}
