package com.user.model;

public class Vehiculo {

    private String idVehiculo;
    private String placa;
    private String marca;
    private String modelo;
    private double capacidadKwh;
    private String tipoConector;
    private double porcentajeBateria;
    private Usuario propietario;
    private int estado;

    public Vehiculo() {
        this.porcentajeBateria = 0;
    }

    public Vehiculo(String idVehiculo,
                     String placa,
                     String marca,
                     String modelo,
                     double capacidadKwh,
                     String tipoConector,
                     double porcentajeBateria,
                     Usuario propietario,
                     int estado) {

        this.idVehiculo = idVehiculo;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.capacidadKwh = capacidadKwh;
        this.tipoConector = tipoConector;
        this.porcentajeBateria = porcentajeBateria;
        this.propietario = propietario;
        this.estado = estado;
    }

    public String getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(String idVehiculo) {
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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



    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    
    public String toDetalle() {
        return "Vehiculo{" + "idVehiculo=" + idVehiculo + ", placa=" + placa + ", marca=" + marca + ", modelo=" + modelo + ", capacidadKwh=" + capacidadKwh + ", tipoConector=" + tipoConector + ", porcentajeBateria=" + porcentajeBateria + ", propietario=" + propietario + ", estado=" + estado + '}';
    }


}