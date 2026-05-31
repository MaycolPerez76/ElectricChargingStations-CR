package com.user.service;

import com.user.model.Usuario;
import com.user.repository.UsuarioRepository;
import java.util.Collection;
import java.util.UUID;

/**
 * Servicio de aplicación para la gestión de usuarios.
 */
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void registrarUsuario(Usuario usuario) {
        validarUsuario(usuario);
        usuarioRepository.guardar(usuario);
    }

    public Usuario obtenerUsuario(UUID id) {
        return usuarioRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + id));
    }

    public Collection<Usuario> listarUsuarios() {
        return usuarioRepository.buscarTodos();
    }

    private void validarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }
        if (usuario.getNombreCompleto() == null || usuario.getNombreCompleto().isBlank()) {
            throw new IllegalArgumentException("El nombre completo es obligatorio");
        }
        if (usuario.getCorreoElectronico() == null || usuario.getCorreoElectronico().isBlank()) {
            throw new IllegalArgumentException("El correo electrónico es obligatorio");
        }
        if (usuario.getIdentificacion() == null || usuario.getIdentificacion().isBlank()) {
            throw new IllegalArgumentException("La identificación es obligatoria");
        }
    }
}
