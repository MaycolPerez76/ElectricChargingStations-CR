package com.user.repository;

import com.user.model.Usuario;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * Abstracción de persistencia para Usuario.
 */
public interface UsuarioRepository {

    void guardar(Usuario usuario);

    Optional<Usuario> buscarPorId(UUID id);

    Collection<Usuario> buscarTodos();
}
