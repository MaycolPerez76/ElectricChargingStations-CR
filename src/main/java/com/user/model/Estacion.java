
package com.user.model;


public class Estacion {

    private int idEstacion;
    private String nombre;
    private String ubicacion;
    private int cantidadCargadores;
    private int cargadoresOcupados;
    private String estado;
    private boolean requiereCita;

    public Estacion() {
    }

    public Estacion(int idEstacion, String nombre, String ubicacion,
    int cantidadCargadores, int cargadoresOcupados,
    String estado, boolean requiereCita) {

    this.idEstacion = idEstacion;
    this.nombre = nombre;
    this.ubicacion = ubicacion;
    this.cantidadCargadores = cantidadCargadores;
    this.cargadoresOcupados = cargadoresOcupados;
    this.estado = estado;
    this.requiereCita = requiereCita;
    
    }

    public int getIdEstacion() {
        return idEstacion;
    }

    public void setIdEstacion(int idEstacion) {
        this.idEstacion = idEstacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getCantidadCargadores() {
        return cantidadCargadores;
    }

    public void setCantidadCargadores(int cantidadCargadores) {
        this.cantidadCargadores = cantidadCargadores;
    }

    public int getCargadoresOcupados() {
        return cargadoresOcupados;
    }

    public void setCargadoresOcupados(int cargadoresOcupados) {
        this.cargadoresOcupados = cargadoresOcupados;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isRequiereCita() {
        return requiereCita;
    }

    public void setRequiereCita(boolean requiereCita) {
        this.requiereCita = requiereCita;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
