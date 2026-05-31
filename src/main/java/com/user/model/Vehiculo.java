

package com.user.model;

public class Vehiculo {

    private int idVehiculo;
    private String placa;
    private String marca;
    private String modelo;
    private int anno;
    private Persona propietario;

    public Vehiculo() {
    }

    public Vehiculo(int idVehiculo, String placa, String marca,
    String modelo, int anno, Persona propietario) {
             
    this.idVehiculo = idVehiculo;
    this.placa = placa;
    this.marca = marca;
    this.modelo = modelo;
    this.anno = anno;
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

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public Persona getPropietario() {
        return propietario;
    }

    public void setPropietario(Persona propietario) {
        this.propietario = propietario;
    }

    @Override
    public String toString() {
        return placa;
    }
}
