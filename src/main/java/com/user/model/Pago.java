package com.user.model;

public class Pago {

    private String idPago;
    private Carga carga;
    private double monto;
    private String metodoPago;
    private String fechaPago;
    private int estado;

    public Pago() {
    }

    public Pago(String idPago,
                Carga carga,
                double monto,
                String metodoPago,
                String fechaPago,
                int estado) {

        this.idPago = idPago;
        this.carga = carga;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.fechaPago = fechaPago;
        this.estado = estado;
    }

    public String getIdPago() {
        return idPago;
    }

    public Carga getCarga() {
        return carga;
    }

    public void setCarga(Carga carga) {
        this.carga = carga;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String toDetalle() {
        return "Pago{" +
                "idPago=" + idPago +
                ", carga=" + carga +
                ", monto=" + monto +
                ", metodoPago=" + metodoPago +
                ", fechaPago=" + fechaPago +
                ", estado=" + estado +
                '}';
    }
}