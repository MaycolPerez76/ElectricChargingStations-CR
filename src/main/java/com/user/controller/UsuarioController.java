package com.user.controller;

import com.user.model.Usuario;
import com.user.service.UsuarioService;
import java.util.Collection;
import java.util.UUID;

/**
 * Controlador básico para operaciones de Usuario.
 */
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void registrarUsuario(Usuario usuario) {
        usuarioService.registrarUsuario(usuario);
    }

    public Usuario obtenerUsuario(String id) {
        return usuarioService.obtenerUsuario(UUID.fromString(id));
    }

    public Collection<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }
}
