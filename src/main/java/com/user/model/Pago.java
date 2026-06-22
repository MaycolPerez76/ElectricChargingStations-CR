package com.user.model;

public class Pago {

    private int idPago;
    private double monto;
    private String metodoPago;
    private String fechaPago;
    private String estado;

    public Pago() {
    }

    public Pago(int idPago, double monto, String metodoPago,
                String fechaPago, String estado) {
        this.idPago = idPago;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.fechaPago = fechaPago;
        this.estado = estado;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}