package com.user.model;

public class Vehiculo {

    private int idVehiculo;
    private String placa;
    private String marca;
    private String modelo;
    private double capacidadKwh;
    private String tipoConector;
    private double porcentajeBateria;
    private boolean activo; // Indica si el vehículo se encuentra activo dentro del sistema.
    private Usuario propietario;

    public Vehiculo() {
        this.activo = true;  
        this.porcentajeBateria = 0;
    }

    public Vehiculo(int idVehiculo,
                     String placa,
                     String marca,
                     String modelo,
                     double capacidadKwh,
                     String tipoConector,
                     double porcentajeBateria,
                     boolean activo,
                     Usuario propietario) {

        this.idVehiculo = idVehiculo;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.capacidadKwh = capacidadKwh;
        this.tipoConector = tipoConector;
        this.porcentajeBateria = porcentajeBateria;
        this.activo = activo;
        this.propietario = propietario;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getCapacidadKwh() {
        return capacidadKwh;
    }

    public void setCapacidadKwh(double capacidadKwh) {
        this.capacidadKwh = capacidadKwh;
    }

    public String getTipoConector() {
        return tipoConector;
    }

    public void setTipoConector(String tipoConector) {
        this.tipoConector = tipoConector;
    }

    public double getPorcentajeBateria() {
        return porcentajeBateria;
    }

    public void setPorcentajeBateria(double porcentajeBateria) {

        if (porcentajeBateria < 0) {
            porcentajeBateria = 0;
        }

        if (porcentajeBateria > 100) {
            porcentajeBateria = 100;
        }

        this.porcentajeBateria = porcentajeBateria;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    @Override
    public String toString() {
        return placa + " - " + marca + " " + modelo;
    }
}