package com.user.model;

public class Carga {

    private int idCarga;
    private String fecha;
    private double energiaConsumida;
    private double duracion;
    private String estado;

    public Carga() {
    }

    public Carga(int idCarga, String fecha, double energiaConsumida,
                 double duracion, String estado) {
        this.idCarga = idCarga;
        this.fecha = fecha;
        this.energiaConsumida = energiaConsumida;
        this.duracion = duracion;
        this.estado = estado;
    }

    public int getIdCarga() {
        return idCarga;
    }

    public void setIdCarga(int idCarga) {
        this.idCarga = idCarga;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getEnergiaConsumida() {
        return energiaConsumida;
    }

    public void setEnergiaConsumida(double energiaConsumida) {
        this.energiaConsumida = energiaConsumida;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}