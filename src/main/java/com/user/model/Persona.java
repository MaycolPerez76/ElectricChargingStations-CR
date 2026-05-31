package com.user.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Entidad base de persona/usuario del sistema.
 * Evita duplicidad de datos personales y puede reutilizarse
 * desde otras entidades del dominio.
 */
public class Persona {

    private UUID id;
    private String nombreCompleto;
    private String identificacion;
    private String correoElectronico;
    private String telefono;
    private String direccion;

    public Persona() {
        this.id = UUID.randomUUID();
    }

    public Persona(String nombreCompleto,
                   String identificacion,
                   String correoElectronico,
                   String telefono,
                   String direccion) {
        this.id = UUID.randomUUID();
        this.nombreCompleto = nombreCompleto;
        this.identificacion = identificacion;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Persona(UUID id,
                   String nombreCompleto,
                   String identificacion,
                   String correoElectronico,
                   String telefono,
                   String direccion) {
        this.id = id != null ? id : UUID.randomUUID();
        this.nombreCompleto = nombreCompleto;
        this.identificacion = identificacion;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public UUID getIdPersona() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona persona)) return false;
        return Objects.equals(id, persona.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
