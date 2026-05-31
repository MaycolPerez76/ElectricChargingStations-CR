package com.user.model;

import java.util.UUID;

/**
 * Entidad Usuario del sistema.
 * Hereda los datos comunes desde Persona para evitar duplicidad.
 */
public class Usuario extends Persona {

    private boolean activo;

    public Usuario() {
        super();
        this.activo = true;
    }

    public Usuario(String nombreCompleto,
                   String identificacion,
                   String correoElectronico,
                   String telefono,
                   String direccion) {
        super(nombreCompleto, identificacion, correoElectronico, telefono, direccion);
        this.activo = true;
    }

    public Usuario(UUID id,
                   String nombreCompleto,
                   String identificacion,
                   String correoElectronico,
                   String telefono,
                   String direccion,
                   boolean activo) {
        super(id, nombreCompleto, identificacion, correoElectronico, telefono, direccion);
        this.activo = activo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
