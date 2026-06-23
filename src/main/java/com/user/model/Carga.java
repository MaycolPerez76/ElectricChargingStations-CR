package com.user.model;

public class Carga {

    private String idCarga;
    private Vehiculo vehiculo;
    private Estacion estacion;
    private double energiaConsumida;
    private double tiempoCarga;
    private String fecha;
    private int estado;

    public Carga() {
    }

    public Carga(String idCarga,
                 Vehiculo vehiculo,
                 Estacion estacion,
                 double energiaConsumida,
                 double tiempoCarga,
                 String fecha,
                 int estado) {

        this.idCarga = idCarga;
        this.vehiculo = vehiculo;
        this.estacion = estacion;
        this.energiaConsumida = energiaConsumida;
        this.tiempoCarga = tiempoCarga;
        this.fecha = fecha;
        this.estado = estado;
    }

    public String getIdCarga() {
        return idCarga;
    }

    public void setIdCarga(String idCarga) {
        this.idCarga = idCarga;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Estacion getEstacion() {
        return estacion;
    }

    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }

    public double getEnergiaConsumida() {
        return energiaConsumida;
    }

    public void setEnergiaConsumida(double energiaConsumida) {
        this.energiaConsumida = energiaConsumida;
    }

    public double getTiempoCarga() {
        return tiempoCarga;
    }

    public void setTiempoCarga(double tiempoCarga) {
        this.tiempoCarga = tiempoCarga;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String toDetalle() {
        return "Carga{" +
                "idCarga=" + idCarga +
                ", vehiculo=" + vehiculo.getPlaca() +
                ", estacion=" + estacion.getNombre() +
                ", energiaConsumida=" + energiaConsumida +
                ", tiempoCarga=" + tiempoCarga +
                ", fecha=" + fecha +
                ", estado=" + estado +
                '}';
    }

    @Override
    public String toString() {
        return toDetalle();
    }
}
