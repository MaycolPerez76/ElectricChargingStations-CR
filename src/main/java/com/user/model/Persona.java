
package com.user.model;

public class Persona {

    private int    idPersona;
    private String nombreCompleto;
    private String identificacion;
    private String correoElectronico;
    private String telefono;

    public Persona() {
    }

    public Persona(int idPersona, String nombreCompleto,
    String identificacion, String correoElectronico,
    String telefono, String direccion) {
        
    this.idPersona = idPersona;
    this.nombreCompleto = nombreCompleto;
    this.identificacion = identificacion;
    this.correoElectronico = correoElectronico;
    this.telefono = telefono;
    this.direccion = direccion;
  
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
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
        return nombreCompleto;
    }
}
