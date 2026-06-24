package com.user.model;

public class Carga {

    private String idCarga;
    private Estacion estacion;
    private double precioRegular;
    private double energiaConsumida;
    private double tiempoCarga;
    
    private int estado;

    public Carga() {
    }

    private Vehiculo vehiculo;
    private String fecha;

    public Carga(String idCarga,
                 Vehiculo vehiculo,
                 Estacion estacion,
                 double precioRegular,
                 double energiaConsumida,
                 double tiempoCarga,
                 String fecha,
                 int estado) {

        this.idCarga = idCarga;
        this.vehiculo = vehiculo;
        this.estacion = estacion;
        this.precioRegular = precioRegular;
        this.energiaConsumida = energiaConsumida;
        this.tiempoCarga = tiempoCarga;
        this.fecha = fecha;
        this.estado = estado;
    }

    public String getIdCarga() {
        return idCarga;
    }

    public double getPrecioRegular() {
        return precioRegular;
    }

    public void setPrecioRegular(double precioRegular) {
        this.precioRegular = precioRegular;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public String getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Carga{" +
                "idCarga=" + idCarga +
                ", estacion=" + estacion.getNombre() +
                ", precioRegular=" + precioRegular +           
                ", energiaConsumida=" + energiaConsumida +
                ", tiempoCarga=" + tiempoCarga +
                ", estado=" + estado +
                '}';
    }
}
